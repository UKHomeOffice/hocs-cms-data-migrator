package uk.gov.digital.ho.hocs.cms.document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

class CaseMigrationMessageTest {
        private final ObjectMapper objectMapper = new ObjectMapper();
        private final JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);

        @Test
        public void testMigrationMessage() {
            CaseDetails caseMigration = buildCaseDetails();
            InputStream schemaStream = inputStreamFromClasspath("hocs-migration-schema.json");
            JsonSchema schema = schemaFactory.getSchema(schemaStream);
            String migrationMessage;

            try {
                migrationMessage = objectMapper.writeValueAsString(caseMigration);
                System.out.println(migrationMessage);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            JsonNode migrationJsonNode;
            try {
                migrationJsonNode = objectMapper.readTree(migrationMessage);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            Set<ValidationMessage> validationResult = schema.validate(migrationJsonNode);
            if (validationResult.isEmpty()) {
                Assert.assertTrue(migrationMessage.length() < 256000);
            } else {
                for (ValidationMessage validationMessage : validationResult) {
                    System.out.println(validationMessage.getMessage());
                }
                fail();
            }
        }

        private CaseDetails buildCaseDetails() {
            CaseDataItem caseDataItem = new CaseDataItem();
            caseDataItem.setName("type");
            caseDataItem.setValue("cms");

            CaseDataItem caseDataItem1 = new CaseDataItem();
            caseDataItem1.setName("test");
            caseDataItem1.setValue("test");

            CaseAttachment caseAttachment = new CaseAttachment();
            caseAttachment.setDocumentType("pdf");
            caseAttachment.setPath("s3://");
            caseAttachment.setLabel("letter");

            CaseDetails caseDetails = new CaseDetails();
            caseDetails.setCaseType("cms");
            caseDetails.addCaseDataItem(caseDataItem);
            caseDetails.addCaseDataItem(caseDataItem1);
            caseDetails.setSourceCaseId("001");
            caseDetails.setCreationDate("2020-07-01");
            caseDetails.setCaseStatus("Closed");
            caseDetails.setCaseStatusDate("2020-07-01");
            caseDetails.setCorrespondenceEmail("test@email.com");
            caseDetails.setCorrespondentName("test");
            caseDetails.addCaseAttachment(caseAttachment);
            return caseDetails;
        }

        private static InputStream inputStreamFromClasspath(String path) {
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        }
    }


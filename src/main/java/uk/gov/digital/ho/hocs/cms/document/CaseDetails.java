package uk.gov.digital.ho.hocs.cms.document;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CaseDetails {

    private String caseType;
    private String sourceCaseId;
    private String correspondentName;
    private String correspondenceEmail;
    private String caseStatus;
    private String caseStatusDate;
    private String creationDate;
    private List<CaseDataItem> caseData;
    private List<CaseAttachment> caseAttachments;
}

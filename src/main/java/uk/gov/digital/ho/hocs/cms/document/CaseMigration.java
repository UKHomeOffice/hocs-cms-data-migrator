package uk.gov.digital.ho.hocs.cms.document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaseMigration {

    private String messageType;
    private CaseDetails caseDetails;

}

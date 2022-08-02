package uk.gov.digital.ho.hocs.cms.document;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CaseDetails {

    public CaseDetails() {
        this.caseData = new ArrayList<>();
        this.caseAttachments = new ArrayList<>();
    }
    public CaseDetails(List<CaseDataItem> caseData, List<CaseAttachment> caseAttachments) {
        this.caseData = caseData;
        this.caseAttachments = caseAttachments;
    }

    private String caseType;
    private String sourceCaseId;
    private String correspondentName;
    private String correspondenceEmail;
    private String caseStatus;
    private String caseStatusDate;
    private String creationDate;
    private List<CaseDataItem> caseData;
    private List<CaseAttachment> caseAttachments;

    public void addCaseDataItem(CaseDataItem dataItem) {
        caseData.add(dataItem);
    }

    public void addCaseAttachment(CaseAttachment attachment) {
        caseAttachments.add(attachment);
    }
}

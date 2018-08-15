
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Defendant" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraDefendantType"/>
 *         &lt;element name="CaseDetail" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraCaseType"/>
 *         &lt;element name="Solicitor" type="{http://www.justice.gov.uk/2013/11/magistrates}MaatSolicitorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "defendant",
    "caseDetail",
    "solicitor"
})
@XmlRootElement(name = "MaatToLibraCaseUpdateRequest")
public class MaatToLibraCaseUpdateRequest {

    @XmlElement(name = "Defendant", required = true)
    protected LibraDefendantType defendant;
    @XmlElement(name = "CaseDetail", required = true)
    protected LibraCaseType caseDetail;
    @XmlElement(name = "Solicitor", required = true)
    protected MaatSolicitorType solicitor;

    /**
     * Gets the value of the defendant property.
     * 
     * @return
     *     possible object is
     *     {@link LibraDefendantType }
     *     
     */
    public LibraDefendantType getDefendant() {
        return defendant;
    }

    /**
     * Sets the value of the defendant property.
     * 
     * @param value
     *     allowed object is
     *     {@link LibraDefendantType }
     *     
     */
    public void setDefendant(LibraDefendantType value) {
        this.defendant = value;
    }

    /**
     * Gets the value of the caseDetail property.
     * 
     * @return
     *     possible object is
     *     {@link LibraCaseType }
     *     
     */
    public LibraCaseType getCaseDetail() {
        return caseDetail;
    }

    /**
     * Sets the value of the caseDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link LibraCaseType }
     *     
     */
    public void setCaseDetail(LibraCaseType value) {
        this.caseDetail = value;
    }

    /**
     * Gets the value of the solicitor property.
     * 
     * @return
     *     possible object is
     *     {@link MaatSolicitorType }
     *     
     */
    public MaatSolicitorType getSolicitor() {
        return solicitor;
    }

    /**
     * Sets the value of the solicitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaatSolicitorType }
     *     
     */
    public void setSolicitor(MaatSolicitorType value) {
        this.solicitor = value;
    }

}

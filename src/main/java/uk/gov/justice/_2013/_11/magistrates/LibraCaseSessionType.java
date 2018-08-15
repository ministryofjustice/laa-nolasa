
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LibraCaseSessionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraCaseSessionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CaseDetail" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraCaseType"/>
 *         &lt;element name="CaseSession" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraSessionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LibraCaseSessionType", propOrder = {
    "caseDetail",
    "caseSession"
})
public class LibraCaseSessionType {

    @XmlElement(name = "CaseDetail", required = true)
    protected LibraCaseType caseDetail;
    @XmlElement(name = "CaseSession")
    protected LibraSessionType caseSession;

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
     * Gets the value of the caseSession property.
     * 
     * @return
     *     possible object is
     *     {@link LibraSessionType }
     *     
     */
    public LibraSessionType getCaseSession() {
        return caseSession;
    }

    /**
     * Sets the value of the caseSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link LibraSessionType }
     *     
     */
    public void setCaseSession(LibraSessionType value) {
        this.caseSession = value;
    }

}

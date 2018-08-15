
package uk.gov.justice._2013._11.magistrates;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Defendant" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraDefendantType"/>
 *         &lt;element name="CaseResult" type="{http://www.justice.gov.uk/2013/11/magistrates}LibraCaseSessionType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultItem", propOrder = {
    "defendant",
    "caseResult"
})
public class ResultItem {

    @XmlElement(name = "Defendant", required = true)
    protected LibraDefendantType defendant;
    @XmlElement(name = "CaseResult", required = true)
    protected List<LibraCaseSessionType> caseResult;

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
     * Gets the value of the caseResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the caseResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCaseResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LibraCaseSessionType }
     * 
     * 
     */
    public List<LibraCaseSessionType> getCaseResult() {
        if (caseResult == null) {
            caseResult = new ArrayList<LibraCaseSessionType>();
        }
        return this.caseResult;
    }

}

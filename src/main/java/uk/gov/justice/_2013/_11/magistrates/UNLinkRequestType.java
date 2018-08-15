
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UNLinkRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UNLinkRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CJSAreaCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LibraCaseId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MaatCaseId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UNLinkRequestType", propOrder = {
    "cjsAreaCode",
    "libraCaseId",
    "maatCaseId"
})
public class UNLinkRequestType {

    @XmlElement(name = "CJSAreaCode", required = true)
    protected String cjsAreaCode;
    @XmlElement(name = "LibraCaseId", required = true)
    protected String libraCaseId;
    @XmlElement(name = "MaatCaseId", required = true)
    protected String maatCaseId;

    /**
     * Gets the value of the cjsAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCJSAreaCode() {
        return cjsAreaCode;
    }

    /**
     * Sets the value of the cjsAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCJSAreaCode(String value) {
        this.cjsAreaCode = value;
    }

    /**
     * Gets the value of the libraCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibraCaseId() {
        return libraCaseId;
    }

    /**
     * Sets the value of the libraCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibraCaseId(String value) {
        this.libraCaseId = value;
    }

    /**
     * Gets the value of the maatCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaatCaseId() {
        return maatCaseId;
    }

    /**
     * Sets the value of the maatCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaatCaseId(String value) {
        this.maatCaseId = value;
    }

}

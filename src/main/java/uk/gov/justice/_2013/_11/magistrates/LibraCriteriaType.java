
package uk.gov.justice._2013._11.magistrates;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LibraCriteriaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LibraCriteriaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LibraCaseId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CJSAreaCode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ASN" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NINO" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="9"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Surname" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Forename" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SearchType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DateOfHearing" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DOB" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CourtHearingLocation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="7"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SearchPattern">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="5"/>
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
@XmlType(name = "LibraCriteriaType", propOrder = {
    "libraCaseId",
    "cjsAreaCode",
    "asn",
    "nino",
    "surname",
    "forename",
    "searchType",
    "dateOfHearing",
    "dob",
    "courtHearingLocation",
    "searchPattern"
})
public class LibraCriteriaType {

    @XmlElement(name = "LibraCaseId")
    protected String libraCaseId;
    @XmlElement(name = "CJSAreaCode")
    protected String cjsAreaCode;
    @XmlElement(name = "ASN")
    protected String asn;
    @XmlElement(name = "NINO")
    protected String nino;
    @XmlElement(name = "Surname")
    protected String surname;
    @XmlElement(name = "Forename")
    protected String forename;
    @XmlElement(name = "SearchType")
    protected int searchType;
    @XmlElement(name = "DateOfHearing")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfHearing;
    @XmlElement(name = "DOB")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dob;
    @XmlElement(name = "CourtHearingLocation")
    protected String courtHearingLocation;
    @XmlElement(name = "SearchPattern")
    protected int searchPattern;

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
     * Gets the value of the asn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASN() {
        return asn;
    }

    /**
     * Sets the value of the asn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASN(String value) {
        this.asn = value;
    }

    /**
     * Gets the value of the nino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNINO() {
        return nino;
    }

    /**
     * Sets the value of the nino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNINO(String value) {
        this.nino = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the forename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the value of the forename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForename(String value) {
        this.forename = value;
    }

    /**
     * Gets the value of the searchType property.
     * 
     */
    public int getSearchType() {
        return searchType;
    }

    /**
     * Sets the value of the searchType property.
     * 
     */
    public void setSearchType(int value) {
        this.searchType = value;
    }

    /**
     * Gets the value of the dateOfHearing property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfHearing() {
        return dateOfHearing;
    }

    /**
     * Sets the value of the dateOfHearing property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfHearing(XMLGregorianCalendar value) {
        this.dateOfHearing = value;
    }

    /**
     * Gets the value of the dob property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDOB() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDOB(XMLGregorianCalendar value) {
        this.dob = value;
    }

    /**
     * Gets the value of the courtHearingLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourtHearingLocation() {
        return courtHearingLocation;
    }

    /**
     * Sets the value of the courtHearingLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourtHearingLocation(String value) {
        this.courtHearingLocation = value;
    }

    /**
     * Gets the value of the searchPattern property.
     * 
     */
    public int getSearchPattern() {
        return searchPattern;
    }

    /**
     * Sets the value of the searchPattern property.
     * 
     */
    public void setSearchPattern(int value) {
        this.searchPattern = value;
    }

}

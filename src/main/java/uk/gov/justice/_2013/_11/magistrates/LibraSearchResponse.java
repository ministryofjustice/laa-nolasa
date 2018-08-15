
package uk.gov.justice._2013._11.magistrates;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="SearchResultItem" type="{http://www.justice.gov.uk/2013/11/magistrates}ResultItem" maxOccurs="50" minOccurs="0"/>
 *         &lt;element ref="{http://www.justice.gov.uk/2013/11/magistrates}AckResponse" minOccurs="0"/>
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
    "searchResultItem",
    "ackResponse"
})
@XmlRootElement(name = "LibraSearchResponse")
public class LibraSearchResponse {

    @XmlElement(name = "SearchResultItem")
    protected List<ResultItem> searchResultItem;
    @XmlElement(name = "AckResponse")
    protected AckResponse ackResponse;

    /**
     * Gets the value of the searchResultItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the searchResultItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSearchResultItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultItem }
     * 
     * 
     */
    public List<ResultItem> getSearchResultItem() {
        if (searchResultItem == null) {
            searchResultItem = new ArrayList<ResultItem>();
        }
        return this.searchResultItem;
    }

    /**
     * Gets the value of the ackResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AckResponse }
     *     
     */
    public AckResponse getAckResponse() {
        return ackResponse;
    }

    /**
     * Sets the value of the ackResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AckResponse }
     *     
     */
    public void setAckResponse(AckResponse value) {
        this.ackResponse = value;
    }

}

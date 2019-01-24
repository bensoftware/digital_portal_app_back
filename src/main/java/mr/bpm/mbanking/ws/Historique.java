
package mr.bpm.mbanking.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour historique complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="historique">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auth_cumu" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="genClearing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recharge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="utilise_cumu" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "historique", propOrder = {
    "authCumu",
    "date",
    "genClearing",
    "host",
    "recharge",
    "utiliseCumu"
})
public class Historique {

    @XmlElement(name = "auth_cumu")
    protected double authCumu;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected String genClearing;
    protected String host;
    protected double recharge;
    @XmlElement(name = "utilise_cumu")
    protected double utiliseCumu;

    /**
     * Obtient la valeur de la propri�t� authCumu.
     * 
     */
    public double getAuthCumu() {
        return authCumu;
    }

    /**
     * D�finit la valeur de la propri�t� authCumu.
     * 
     */
    public void setAuthCumu(double value) {
        this.authCumu = value;
    }

    /**
     * Obtient la valeur de la propri�t� date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * D�finit la valeur de la propri�t� date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propri�t� genClearing.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenClearing() {
        return genClearing;
    }

    /**
     * D�finit la valeur de la propri�t� genClearing.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenClearing(String value) {
        this.genClearing = value;
    }

    /**
     * Obtient la valeur de la propri�t� host.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * D�finit la valeur de la propri�t� host.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Obtient la valeur de la propri�t� recharge.
     * 
     */
    public double getRecharge() {
        return recharge;
    }

    /**
     * D�finit la valeur de la propri�t� recharge.
     * 
     */
    public void setRecharge(double value) {
        this.recharge = value;
    }

    /**
     * Obtient la valeur de la propri�t� utiliseCumu.
     * 
     */
    public double getUtiliseCumu() {
        return utiliseCumu;
    }

    /**
     * D�finit la valeur de la propri�t� utiliseCumu.
     * 
     */
    public void setUtiliseCumu(double value) {
        this.utiliseCumu = value;
    }

}

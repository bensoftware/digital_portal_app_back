
package mr.bpm.mbanking.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour imal complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="imal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="montant" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imal", propOrder = {
    "date",
    "montant",
    "rrn",
    "type"
})
public class Imal {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected double montant;
    protected String rrn;
    protected int type;

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
     * Obtient la valeur de la propri�t� montant.
     * 
     */
    public double getMontant() {
        return montant;
    }

    /**
     * D�finit la valeur de la propri�t� montant.
     * 
     */
    public void setMontant(double value) {
        this.montant = value;
    }

    /**
     * Obtient la valeur de la propri�t� rrn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRrn() {
        return rrn;
    }

    /**
     * D�finit la valeur de la propri�t� rrn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRrn(String value) {
        this.rrn = value;
    }

    /**
     * Obtient la valeur de la propri�t� type.
     * 
     */
    public int getType() {
        return type;
    }

    /**
     * D�finit la valeur de la propri�t� type.
     * 
     */
    public void setType(int value) {
        this.type = value;
    }

}

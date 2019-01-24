
package mr.bpm.mbanking.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour soldeCarte complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="soldeCarte">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authCum" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rechargeCum" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="usedCum" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soldeCarte", propOrder = {
    "authCum",
    "rechargeCum",
    "usedCum"
})
public class SoldeCarte {

    protected double authCum;
    protected double rechargeCum;
    protected double usedCum;

    /**
     * Obtient la valeur de la propri�t� authCum.
     * 
     */
    public double getAuthCum() {
        return authCum;
    }

    /**
     * D�finit la valeur de la propri�t� authCum.
     * 
     */
    public void setAuthCum(double value) {
        this.authCum = value;
    }

    /**
     * Obtient la valeur de la propri�t� rechargeCum.
     * 
     */
    public double getRechargeCum() {
        return rechargeCum;
    }

    /**
     * D�finit la valeur de la propri�t� rechargeCum.
     * 
     */
    public void setRechargeCum(double value) {
        this.rechargeCum = value;
    }

    /**
     * Obtient la valeur de la propri�t� usedCum.
     * 
     */
    public double getUsedCum() {
        return usedCum;
    }

    /**
     * D�finit la valeur de la propri�t� usedCum.
     * 
     */
    public void setUsedCum(double value) {
        this.usedCum = value;
    }

}

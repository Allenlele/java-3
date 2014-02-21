
package com.wds.java.cxf.client.role.code;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAuthorityResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAuthorityResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://server.spring.cxf.wds.com/}mappingUserValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAuthorityResponse", propOrder = {
    "_return"
})
public class GetAuthorityResponse {

    @XmlElement(name = "return")
    protected MappingUserValue _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MappingUserValue }
     *     
     */
    public MappingUserValue getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MappingUserValue }
     *     
     */
    public void setReturn(MappingUserValue value) {
        this._return = value;
    }

}

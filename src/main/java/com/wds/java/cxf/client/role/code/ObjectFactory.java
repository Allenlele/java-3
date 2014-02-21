
package com.wds.java.cxf.client.role.code;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.wds.java.cxf.client.role.code package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAuthorityResponse_QNAME = new QName("http://server.spring.cxf.wds.com/", "getAuthorityResponse");
    private final static QName _GetAuthority_QNAME = new QName("http://server.spring.cxf.wds.com/", "getAuthority");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.wds.java.cxf.client.role.code
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAuthorityResponse }
     * 
     */
    public GetAuthorityResponse createGetAuthorityResponse() {
        return new GetAuthorityResponse();
    }

    /**
     * Create an instance of {@link GetAuthority }
     * 
     */
    public GetAuthority createGetAuthority() {
        return new GetAuthority();
    }

    /**
     * Create an instance of {@link MappingUserValue }
     * 
     */
    public MappingUserValue createMappingUserValue() {
        return new MappingUserValue();
    }

    /**
     * Create an instance of {@link Entry }
     * 
     */
    public Entry createEntry() {
        return new Entry();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthorityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.spring.cxf.wds.com/", name = "getAuthorityResponse")
    public JAXBElement<GetAuthorityResponse> createGetAuthorityResponse(GetAuthorityResponse value) {
        return new JAXBElement<GetAuthorityResponse>(_GetAuthorityResponse_QNAME, GetAuthorityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAuthority }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.spring.cxf.wds.com/", name = "getAuthority")
    public JAXBElement<GetAuthority> createGetAuthority(GetAuthority value) {
        return new JAXBElement<GetAuthority>(_GetAuthority_QNAME, GetAuthority.class, null, value);
    }

}

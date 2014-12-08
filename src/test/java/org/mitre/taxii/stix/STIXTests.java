package org.mitre.taxii.stix;

import java.io.Reader;
import java.io.StringReader;
import javax.xml.bind.JAXBException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mitre.stix.stix_1.STIXType;
import org.mitre.taxii.ContentBindings;
import org.mitre.taxii.messages.xml11.ContentBlock;
import org.mitre.taxii.messages.xml11.MessageHelper;
import org.mitre.taxii.messages.xml11.ObjectFactory;
import org.mitre.taxii.messages.xml11.PollResponse;
import org.mitre.taxii.messages.xml11.TaxiiXml;
import org.mitre.taxii.messages.xml11.TaxiiXmlFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jasenj1
 */
public class STIXTests {
    private final ObjectFactory factory = new ObjectFactory();
    private final TaxiiXmlFactory txf = new TaxiiXmlFactory();
    private final TaxiiXml taxiiXml;
    
    public STIXTests() {
        txf.addJaxbContextPackage(STIXType.class.getPackage().getName());
        taxiiXml = txf.createTaxiiXml();    
    }

    @Test
    public void stixTest() throws JAXBException {
        String stixStr = "<stix:STIX_Package id=\"edge:Package-54c0bd7d-f390-4523-84c2-24f74609e4c6\" timestamp=\"2014-12-05T19:55:36.800902+00:00\" version=\"1.1.1\" xmlns:TOUMarking=\"http://data-marking.mitre.org/extensions/MarkingStructure#Terms_Of_Use-1\" xmlns:cybox=\"http://cybox.mitre.org/cybox-2\" xmlns:cyboxCommon=\"http://cybox.mitre.org/common-2\" xmlns:cyboxVocabs=\"http://cybox.mitre.org/default_vocabularies-2\" xmlns:edge=\"http://soltra.com/\" xmlns:indicator=\"http://stix.mitre.org/Indicator-2\" xmlns:marking=\"http://data-marking.mitre.org/Marking-1\" xmlns:opensource=\"http://hailataxii.com\" xmlns:simpleMarking=\"http://data-marking.mitre.org/extensions/MarkingStructure#Simple-1\" xmlns:stix=\"http://stix.mitre.org/stix-1\" xmlns:stixCommon=\"http://stix.mitre.org/common-1\" xmlns:stixVocabs=\"http://stix.mitre.org/default_vocabularies-1\" xmlns:taxii=\"http://taxii.mitre.org/messages/taxii_xml_binding-1\" xmlns:tdq=\"http://taxii.mitre.org/query/taxii_default_query-1\" xmlns:tlpMarking=\"http://data-marking.mitre.org/extensions/MarkingStructure#TLP-1\" xmlns:ttp=\"http://stix.mitre.org/TTP-1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><stix:STIX_Header><stix:Handling><marking:Marking><marking:Controlled_Structure>../../../../descendant-or-self::node()</marking:Controlled_Structure><marking:Marking_Structure color=\"WHITE\" xsi:type=\"tlpMarking:TLPMarkingStructureType\"/><marking:Marking_Structure xsi:type=\"TOUMarking:TermsOfUseMarkingStructureType\"><TOUMarking:Terms_Of_Use>zeustracker.abuse.ch | Abuse source[https://sslbl.abuse.ch/blacklist/] - As for all abuse.ch projects, the use of the SSL Blacklist is free for both commercial and non-commercial usage without any limitation. However, if you are a commercial vendor of security software/services and you want to integrate data from the SSL Blacklist into your products / services, you will have to ask for permission first by contacting me using the contact form [http://www.abuse.ch/?page_id=4727].'\n" +
"</TOUMarking:Terms_Of_Use>\n" +
"                </marking:Marking_Structure><marking:Marking_Structure xsi:type=\"simpleMarking:SimpleMarkingStructureType\"><simpleMarking:Statement>Unclassified (Public)</simpleMarking:Statement>\n" +
"                </marking:Marking_Structure>\n" +
"            </marking:Marking>\n" +
"        </stix:Handling>\n" +
"    </stix:STIX_Header><stix:Indicators><stix:Indicator id=\"opensource:indicator-0906be7f-d338-4698-b804-02819dde4790\" timestamp=\"2014-10-31T16:44:24.721121+00:00\" version=\"2.1.1\" xsi:type=\"indicator:IndicatorType\"><indicator:Title>ZeuS Tracker (online)| 104.192.103.43/vic/helps/file.php (2014-10-22) | This IP address has been identified as malicious by zeustracker.abuse.ch</indicator:Title><indicator:Type xsi:type=\"stixVocabs:IndicatorTypeVocab-1.1\">IP Watchlist</indicator:Type><indicator:Type xsi:type=\"stixVocabs:IndicatorTypeVocab-1.1\">URL Watchlist</indicator:Type><indicator:Type xsi:type=\"stixVocabs:IndicatorTypeVocab-1.1\">File Hash Watchlist</indicator:Type><indicator:Description>This IP address 104.192.103.43 has been identified as malicious by zeustracker.abuse.ch. For more detailed infomation about this indicator go to [CAUTION!!Read-URL-Before-Click] [https://zeustracker.abuse.ch/monitor.php?host=104.192.103.43].</indicator:Description><indicator:Observable idref=\"opensource:Observable-4622f725-c68a-4e2a-a025-44a00b0bdec3\">\n" +
"            </indicator:Observable><indicator:Indicated_TTP><stixCommon:TTP idref=\"opensource:ttp-58e9864e-6c43-4423-a87a-c6b6c6415a2a\" xsi:type=\"ttp:TTPType\"/>\n" +
"            </indicator:Indicated_TTP><indicator:Producer><stixCommon:Identity id=\"opensource:Identity-3dd68bc9-592a-4520-9975-1753ae55562c\"><stixCommon:Name>zeustracker.abuse.ch</stixCommon:Name>\n" +
"                </stixCommon:Identity><stixCommon:Time><cyboxCommon:Produced_Time>2014-10-22T00:00:00+00:00</cyboxCommon:Produced_Time><cyboxCommon:Received_Time>2014-10-22T15:01:33+00:00</cyboxCommon:Received_Time>\n" +
"                </stixCommon:Time>\n" +
"            </indicator:Producer>\n" +
"        </stix:Indicator>\n" +
"    </stix:Indicators>\n" +
"</stix:STIX_Package>";

        Reader reader = new StringReader(stixStr);
//        STIXType st = (STIXType)taxiiXml.getJaxbContext().createUnmarshaller().unmarshal(reader);
        
        ContentBlock cb = factory.createContentBlock()                                    
                                    .withContentBinding(
                                        factory.createContentInstanceType().withBindingId(ContentBindings.CB_STIX_XML_111)
                                    )
                                    .withContent(
                                        factory.createAnyMixedContentType()
                                            //    .withContent(st)
                                    );
        
        PollResponse pr = factory.createPollResponse()
                .withMessageId(MessageHelper.generateMessageId())
                .withInResponseTo("nothing")
                .withCollectionName("test")
                .withContentBlocks(cb);
        
        assertTrue(true);
    }
    
}
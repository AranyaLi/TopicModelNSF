/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tika.parser.geo.topic;

/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;*/
import org.junit.Test;
import junit.framework.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class GeoParserTest {

	private Parser geoparser = new GeoParser();
	@Test
	public void testFunctions() throws UnsupportedEncodingException, IOException, SAXException, TikaException{
		String text =
            "The millennial-scale cooling trend that followed the HTM coincides with the decrease in Northern Hemisphere " +
            "summer insolation driven by slow changes in Earth's orbit. Despite the nearly linear forcing, the transition from the HTM to "+
            "the Little Ice Age (1500-1900 AD) was neither gradual nor uniform. To understand how feedbacks and perturbations result in rapid changes, " +
            "a geographically distributed network of proxy climate records was examined to study the spatial and temporal patterns of change, and to " + 
            "quantify the magnitude of change during these transitions. During the HTM, summer sea-ice cover over the Arctic Ocean was likely the smallest of "+
            "the present interglacial period; certainly it was less extensive than at any time in the past 100 years, "+
            "and therefore affords an opportunity to investigate a period of warmth similar to what is projected during the coming century.";
	
        Metadata metadata = new Metadata();
        ParseContext context=new ParseContext();
        final String gazetteer="src/main/java/org/apache/tika/parser/geo/topic/model/allCountries.txt";
        final String nerPath="src/main/java/org/apache/tika/parser/geo/topic/model/en-ner-location.bin";
        new GeoParser().init(gazetteer, nerPath);
        geoparser.parse(
                new ByteArrayInputStream(text.getBytes("ISO-8859-1")),
                new BodyContentHandler(),
                metadata,
                context);
        Assert.assertNull(metadata.get(metadata.get("Geographic_NAME")));
        Assert.assertNull(metadata.get(metadata.get("Geographic_LONGITUDE")));
        Assert.assertNull(metadata.get(metadata.get("Geographic_LATITUDE")));
	}	
}

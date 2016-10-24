@XmlJavaTypeAdapters({
		@XmlJavaTypeAdapter(type = ZonedDateTime.class,
				value = ZonedDateTimeXmlAdapter.class)
})
package com.mitchellbosecke.benchmark.model;

import java.time.ZonedDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import com.migesok.jaxb.adapter.javatime.ZonedDateTimeXmlAdapter;

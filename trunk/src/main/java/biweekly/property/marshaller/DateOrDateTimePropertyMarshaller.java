package biweekly.property.marshaller;

import java.util.Date;
import java.util.List;

import biweekly.parameter.ICalParameters;
import biweekly.parameter.Value;
import biweekly.property.DateOrDateTimeProperty;
import biweekly.util.ICalDateFormatter;
import biweekly.util.ISOFormat;


/*
 Copyright (c) 2013, Michael Angstadt
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Marshals properties that have either "date" or "date/time" values.
 * @author Michael Angstadt
 */
public abstract class DateOrDateTimePropertyMarshaller<T extends DateOrDateTimeProperty> extends ICalPropertyMarshaller<T> {
	//TODO allow a date to be written using a timezone instead of "Z"
	public DateOrDateTimePropertyMarshaller(Class<T> clazz, String propertyName) {
		super(clazz, propertyName);
	}

	@Override
	protected void _prepareParameters(T property, ICalParameters copy) {
		if (property.getValue() != null && !property.hasTime()) {
			copy.setValue(Value.DATE);
		}
	}

	@Override
	protected String _writeText(T property, List<String> warnings) {
		Date value = property.getValue();
		if (value == null) {
			return "";
		}

		ISOFormat format = property.hasTime() ? ISOFormat.UTC_TIME_BASIC : ISOFormat.DATE_BASIC;
		return ICalDateFormatter.format(value, format);
	}

	@Override
	protected T _parseText(String value, ICalParameters parameters, List<String> warnings) {
		value = unescape(value);

		Date date = null;
		boolean hasTime = false;
		try {
			date = ICalDateFormatter.parse(value);
			hasTime = value.contains("T");
		} catch (IllegalArgumentException e) {
			//TODO marshal as RawProperty instead so data isn't lost
			warnings.add("Could not parse date value: " + value);
		}

		return newInstance(date, hasTime);
	}

	protected abstract T newInstance(Date date, boolean hasTime);
}
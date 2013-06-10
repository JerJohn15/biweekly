package biweekly.property;

import java.util.Date;

import biweekly.parameter.Range;


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
 * Records the original value of the {@link DateStart} property if a recurrence
 * instance has been modified. Used in conjunction with the {@link Uid} and
 * {@link Sequence} properties to uniquely identify a recurrence instance.
 * @author Michael Angstadt
 * @see "RFC 5545 p.112-4"
 */
public class RecurrenceId extends DateOrDateTimeProperty {
	/**
	 * Creates a date end property.
	 * @param endDate the end date
	 * @param hasTime true to include the time component of the date, false not
	 * to
	 */
	public RecurrenceId(Date endDate, boolean hasTime) {
		super(endDate, hasTime);
	}

	@Override
	public String getTimezoneId() {
		return super.getTimezoneId();
	}

	@Override
	public void setTimezoneId(String timezoneId) {
		super.setTimezoneId(timezoneId);
	}

	/**
	 * Gets the effective range of recurrence instances from the instance
	 * specified by this property.
	 * @return the range or null if not set
	 */
	public Range getRange() {
		return parameters.getRange();
	}

	/**
	 * Sets the effective range of recurrence instances from the instance
	 * specified by this property.
	 * @param range the range or null to remove
	 */
	public void setRange(Range range) {
		parameters.setRange(range);
	}
}
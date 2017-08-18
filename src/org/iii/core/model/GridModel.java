/**
 * 
 */
package org.iii.core.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.collect.Lists;

/**
 * @author Shock
 *
 */
@SuppressWarnings({ "serial" })
public class GridModel<T extends Serializable> implements Serializable {

	/** total page. */
	private long total = 0;

	/** total tecord *. */
	private long records;

	/** The page. */
	private int page = 1;

	/** The rows. */
	private List<T> rows = Lists.newArrayList();

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total the new total
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 * 
	 * @param page
	 *            the new page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Gets the rows.
	 * 
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * Sets the rows.
	 * 
	 * @param rows
	 *            the new rows
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Gets the records.
	 * 
	 * @return the records
	 */
	public long getRecords() {
		return records;
	}

	/**
	 * Sets the records.
	 * 
	 * @param records
	 *            the new records
	 */
	public void setRecords(long records) {
		this.records = records;
	}
}

package org.iii.core.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.iii.core.entity.GenericCodeNameEntity;
import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.SystemStatus;
import org.iii.core.util.CharConstant;
import org.springframework.util.Assert;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * @author Shock
 *
 */
@SuppressWarnings("serial")
public class LabelValueModel extends GenericModel {

	/** The label. */
	private String label;

	/** The value. */
	private String value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tw.com.softleader.core.model.GenericModel#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("label", label).append("value", value).toString();
	}

	/**
	 * Instantiates a new label value model.
	 */
	public LabelValueModel() {
		super();
	}

	/**
	 * Instantiates a new label value model.
	 * 
	 * @param label
	 *            the label
	 * @param value
	 *            the value
	 */
	public LabelValueModel(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 * 
	 * @param label
	 *            the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the label value model.
	 * 
	 * @param label
	 *            the label
	 * @param value
	 *            the value
	 * @return the label value model
	 */
	public static LabelValueModel getLabelValueModel(String label, String value) {
		return new LabelValueModel(label, value);
	}

	/**
	 * As list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param iterable
	 *            the iterable
	 * @param function
	 *            the function
	 * @param predicates
	 *            the predicates
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<LabelValueModel> asList(Iterable<T> iterable, final Function<? super T, LabelValueModel> function,
			final Predicate<? super T>... predicates) {
		if (iterable == null) {
			return null;
		}

		Assert.notNull(function);

		if (predicates != null) {
			for (Predicate<? super T> predicate : predicates) {
				iterable = Iterables.filter(iterable, predicate);
			}
		}

		return Lists.newArrayList(Iterables.transform(iterable, function));
	}

	/** The Constant IS_ACTIVE. */
	public static final Predicate<GenericEntity> IS_ACTIVE = new Predicate<GenericEntity>() {
		@Override
		public boolean apply(GenericEntity e) {
			return e.getStatus() == SystemStatus.Y;
		}
	};

	/** The Constant AS_CODE_NAME. */
	public static final Function<GenericCodeNameEntity, LabelValueModel> AS_CODE_NAME = new Function<GenericCodeNameEntity, LabelValueModel>() {
		@Override
		public LabelValueModel apply(GenericCodeNameEntity e) {
			return LabelValueModel.getLabelValueModel(e.getCode() + CharConstant.DASH + e.getLocalName(), e.getCode());
		}
	};

	/** The Constant AS_ID_NAME. */
	public static final Function<GenericCodeNameEntity, LabelValueModel> AS_ID_NAME = new Function<GenericCodeNameEntity, LabelValueModel>() {
		@Override
		public LabelValueModel apply(GenericCodeNameEntity e) {
			return LabelValueModel.getLabelValueModel(e.getCode() + CharConstant.DASH + e.getLocalName(), e.getId().toString());
		}
	};

}

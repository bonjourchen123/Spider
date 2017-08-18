/**
 * 
 */
package org.iii.core.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.iii.core.model.LabelValueModel;

/**
 * @author Shock
 *
 */
public enum SystemStatus {

	/** 有效資料. */
	Y("有效資料", "啟用"),

	/** 無效資料. */
	N("無效資料", "停用")
	
	;
	
	private String localName;
	
	private String localName2;
	
	private SystemStatus () {
		
	}
	
	private SystemStatus (String localName, String localName2) {
		this.localName = localName;
		this.localName2 = localName2;
	}
	
	public String getLocalName() {
		return localName;
	}
	
	public String getLocalName2() {
		return localName2;
	}

	public static SystemStatus getByCode(String code) {
		for (SystemStatus status : SystemStatus.values()) {
			if (status.toString().equals(code)) return status;
		}
		return null;
	}

	/**
	 * Values of system.
	 * 
	 * @return the system status[]
	 */
	public static SystemStatus[] valuesOfSystem() {
		return new SystemStatus[] { Y, N };
	}
	
	/**
	 * Values of system.取得Map by French
	 * @deprecated  suggest to use {@link #getSystemLabelList()}
	 */
	@Deprecated
	public static TreeMap<SystemStatus, String> mapOfSystem() {
		TreeMap<SystemStatus,String> mapOfSystem = new TreeMap<SystemStatus,String>();
		mapOfSystem.put(SystemStatus.Y, "有效資料");
		mapOfSystem.put(SystemStatus.N, "無效資料");
		return  mapOfSystem;
	}
	
	/**
	 * @return SystemStatus 列表(有效資料/無效資料)
	 */
	public static List<LabelValueModel> getSystemLabelList() {
		List<LabelValueModel> labelList = new ArrayList<LabelValueModel>();
		for (SystemStatus element : SystemStatus.valuesOfSystem()) {
			labelList.add(new LabelValueModel(element.getLocalName(), element.name()));
		}
		return labelList;
	}
	
	/**
	 * @return SystemStatus 列表(啟用/停用)
	 */
	public static List<LabelValueModel> getSystemLabelListForName2() {
		List<LabelValueModel> labelList = new ArrayList<LabelValueModel>();
		for (SystemStatus element : SystemStatus.valuesOfSystem()) {
			labelList.add(new LabelValueModel(element.getLocalName2(), element.name()));
		}
		return labelList;
	}
}

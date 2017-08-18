package org.iii.module.setting.office.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericCodeNameEntity;

/**
 * @author Shin Chen
 *
 */
@SuppressWarnings("serial")  //有這行就不用定義序列化 EX: private static final long serialVersionUID = 1L;
@Entity
@Table(name ="OFFICE")
public class Office extends GenericCodeNameEntity {



}

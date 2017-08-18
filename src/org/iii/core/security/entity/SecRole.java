package org.iii.core.security.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericCodeNameEntity;

/**
 * @author Kuso
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="SEC_ROLE")
public class SecRole extends GenericCodeNameEntity{

}

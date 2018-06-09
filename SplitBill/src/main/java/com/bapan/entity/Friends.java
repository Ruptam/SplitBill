package com.bapan.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FRNLST")
public class Friends {
	/**
	 * 
	 */
	private FriendsPk friendPk;

	/**
	 * @return the friendPk
	 */
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="friendIdr",column=@Column(name="FRNIDR",length=20)),
		@AttributeOverride(name="friendOf", column=@Column(name="FRNDOFIDR",length=20))
	})
	public FriendsPk getFriendPk() {
		return friendPk;
	}

	/**
	 * @param friendPk the friendPk to set
	 */
	public void setFriendPk(FriendsPk friendPk) {
		this.friendPk = friendPk;
	}
	
}

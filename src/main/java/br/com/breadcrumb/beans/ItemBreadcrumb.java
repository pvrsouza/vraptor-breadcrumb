package br.com.breadcrumb.beans;

import java.io.Serializable;

public class ItemBreadcrumb implements Serializable {

	private static final long serialVersionUID = 768432246051133685L;
	private String nome;
	private int level;
	private String value;

	public ItemBreadcrumb(String nome, String value, int level) {
		this.nome = nome;
		this.value = value;
		this.level = level;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUri() {
		return value;
	}

	public void setUri(String uri) {
		this.value = uri;
	}
	

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemBreadcrumb other = (ItemBreadcrumb) obj;
		if (level != other.level)
			return false;
		return true;
	}


}

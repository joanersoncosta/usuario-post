package dev.wakandaacademy.credencial.domain.enuns;

public enum TipoPerfil {
	USUARIO("ROLE_USUARIO");
	
	private String perfil;
	
	TipoPerfil(String  perfil){
		this.perfil = perfil;
	}
	
	public String getpPerfil() {
		return this.perfil;
	}
	

}

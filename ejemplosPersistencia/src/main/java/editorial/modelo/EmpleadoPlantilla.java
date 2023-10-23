package editorial.modelo;

import javax.persistence.Column;

public class EmpleadoPlantilla extends Empleado{

	@Column(name="salario")
    private Double salario;

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	
}

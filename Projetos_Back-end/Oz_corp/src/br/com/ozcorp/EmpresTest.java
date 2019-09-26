package br.com.ozcorp;

import br.com.ozcorp.Funcionario;
import br.com.ozcorp.TipoSanguineo;
import br.com.ozcorp.Setor;
import br.com.ozcorp.Encargo;
import br.com.ozcorp.Designacao;
import br.com.ozcorp.Sexo;

public class EmpresTest {

	public static void main(String[] args) {
		Funcionario cc = new Funcionario("Lucas", "551236486", 123546789, 45892, "lucas1-portal@hotmail.com",
				new Encargo(Designacao.ANALISTA, Setor.FINANCEIRO, 1500, 0, "GS"), "284", Sexo.FEMININO,
				TipoSanguineo.O_M);

		System.out.println("Informações do Funcionário:");
		System.out.println("");
		System.out.println("Nome:\t\t\t\t" + cc.getNome());
		System.out.println("RG:\t\t\t\t\t" + cc.getRg());
		System.out.println("CPF:\t\t\t\t" + cc.getCpf());
		System.out.println("Mátricula:\t\t\t" + cc.getInscricao());
		System.out.println("E-mail:\t\t\t\t" + cc.getEmail());
		System.out.println("Senha:\t\t\t\t" + cc.getSenha());
		System.out.print("Cargo:\t\t\t\t" + cc.getCargo().getTitulo().designacao);
		System.out.println(" " + cc.getCargo().getSetor().set);
		System.out.println("Nível:\t\t\t\t" + cc.getCargo().getClasse());
		System.out.println("Sigla:\t\t\t\t" + cc.getCargo().getSigla());
		System.out.println("Salário Base:\t\t" + cc.getCargo().getSalarioBase());
		System.out.println("Tipo Sanguíneo:\t\t" + cc.getTipoSanguineo().tipoSanguineo);
		System.out.println("Sexo:\t\t\t\t" + cc.getSexo().sexo);
	}
}
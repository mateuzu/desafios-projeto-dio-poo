package models;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.impl.Conta;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banco {

	private String nome;
	private List<Conta> contas = new ArrayList<>();

}
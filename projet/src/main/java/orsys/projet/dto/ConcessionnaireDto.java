package orsys.projet.dto;

import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcessionnaireDto extends UtilisateurDto {

	@NonNull
	@Pattern(regexp = "\\d{10}", message = "veuillez un numéro de téléphone composé de 10 chiffres")
	String numeroDeTelephone;
}

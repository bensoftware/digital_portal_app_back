package com.monetique.model.helper.monetique;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.monetique.dto.monetique.AccountDtdo;
import com.monetique.dto.monetique.CardDto;
import com.monetique.dto.monetique.ClientCifDto;
import com.monetique.dto.monetique.TypeCardDto;

@Component
public class ClientCifDtoBinded {


	
	public static  ClientCifDto getClientCifDto(String cif) {
		
		ClientCifDto ClientCifDto=new ClientCifDto();
		ClientCifDto.setAdresse("NKT");
		ClientCifDto.setEmail("benjudoicaelle@gmail.com");
		ClientCifDto.setNom("Judicaelle Ben");
		ClientCifDto.setPrenom("MBOUNGOU");
		ClientCifDto.setNomCarte("M. Judicaelle Ben 849878788799878978984848484899");
		ClientCifDto.setSexe("M");
		ClientCifDto.setTelephone("+222 37 81 80 77");
		ClientCifDto.setNni("54454d56d6d6");
		ClientCifDto.setPrenomPere("MICHELE MBOUNGOU");
		
		ClientCifDto.setImage("iVBORw0KGgoAAAANSUhEUgAAAWAAAACNCAYAAACJzKCkAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NTA3QTEyQTU1NjJEMTFFOTlGOTlEN0I5QTUzRkM2REUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NTA3QTEyQTY1NjJEMTFFOTlGOTlEN0I5QTUzRkM2REUiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo1MDdBMTJBMzU2MkQxMUU5OUY5OUQ3QjlBNTNGQzZERSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo1MDdBMTJBNDU2MkQxMUU5OUY5OUQ3QjlBNTNGQzZERSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PhKumgQAAEWYSURBVHja7H0HnB5V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");
		ClientCifDto.setListcardDtos(getListCardDto());
		ClientCifDto.setListAccountDtdos(getListAccountDtdo());
		ClientCifDto.setListTypeCardDtos(getLisqtTypeCardDto());
		
		return ClientCifDto;
	}
	
	private static List<CardDto> getListCardDto() {
		
	 List<CardDto> listCardtos=new ArrayList<>();
	 
	 CardDto cardDto=new CardDto();
	// CardDto cardDto2=new CardDto();
	 AccountDtdo accounbtDto= new AccountDtdo();
	 accounbtDto.setAdditionalReference("123456789");
	 accounbtDto.setAgence("0001");
		accounbtDto.setCif("458");
		accounbtDto.setDevise("MRU");
		accounbtDto.setGlCode("p555");
		accounbtDto.setLongNameEng("MBOUNGOU  Judicaelle Ben");
		accounbtDto.setSlNo("kdks5455");
	 cardDto.setAccountDtdo(accounbtDto);
	
	 cardDto.setPan("jlkhfdhud");
	 cardDto.setStatus("actif");
	 cardDto.setTypeCardDto(new TypeCardDto("12","VOYAGE"));
	 
	 
	 AccountDtdo accounbtDto2= new AccountDtdo();
	 accounbtDto2.setAdditionalReference("956875234");
	 accounbtDto2.setAgence("0002");
	 accounbtDto2.setCif("458");
	 accounbtDto2.setDevise("MRU");
	 accounbtDto2.setGlCode("4558kk");
	 accounbtDto2.setLongNameEng("MBOUNGOU  Judicaelle Ben");
	 accounbtDto2.setSlNo("po5247");
	 cardDto.setAccountDtdo(accounbtDto2);
	
	 cardDto.setPan("jlkhfdhud");
	 cardDto.setStatus("actif");
	 cardDto.setTypeCardDto(new TypeCardDto("12","VOYAGE"));
	
	 CardDto cardDto2=new CardDto("ldd", "sqs", "actif",new TypeCardDto("18","GOLD"),accounbtDto2);
	 listCardtos.add(cardDto);
	 listCardtos.add(cardDto2);
	return listCardtos;
	}
	
	
	private static List<AccountDtdo> getListAccountDtdo() {
		
		List<AccountDtdo> listAccountDtdos=new ArrayList<>();
		 
		AccountDtdo accountDtdo =new AccountDtdo();
		accountDtdo.setAdditionalReference("123654879");
		accountDtdo.setAgence("0001");
		accountDtdo.setCif("458");
		accountDtdo.setDevise("MRU");
		accountDtdo.setGlCode("kjKJ");
		accountDtdo.setLongNameEng("MBOUNGOU Judicaelle Ben");
		accountDtdo.setSlNo("kdks5455");
		
		AccountDtdo accountDtdo2 =new AccountDtdo();
		accountDtdo2.setAdditionalReference("0000002568");
		accountDtdo2.setAgence("0002");
		accountDtdo2.setCif("458");
		accountDtdo2.setDevise("MRU");
		accountDtdo2.setGlCode("kjKJ");
		accountDtdo2.setLongNameEng("MBOUNGOU Judicaelle Ben");
		accountDtdo2.setSlNo("kdks5455");
		
		listAccountDtdos.add(accountDtdo);
		listAccountDtdos.add(accountDtdo2);
		
		
		return listAccountDtdos;
	}
	
	
	public static  TypeCardDto getTypeCardDto() {
		TypeCardDto typeCardDto=new TypeCardDto();
		typeCardDto.setId("djdj");
		typeCardDto.setLibelle("Je suis ici pour taffer");
		return typeCardDto;
	}
	
	public static  List<TypeCardDto> getLisqtTypeCardDto() {
		TypeCardDto typeCardDto=new TypeCardDto();
		typeCardDto.setId("45s");
		typeCardDto.setLibelle("GIMTEL");
		
		TypeCardDto typeCardDto2=new TypeCardDto();
		typeCardDto2.setId("djdj");
		typeCardDto2.setLibelle("MASTERCARD VOYAGE");
		List<TypeCardDto> listTypeCardDto =new ArrayList<>();
		
		listTypeCardDto.add(typeCardDto);
		listTypeCardDto.add(typeCardDto2);
		return listTypeCardDto;
	}
	
	
	
	
	
	
}

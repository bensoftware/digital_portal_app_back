package com.monetique.um.dao.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.monetique.um.dao.entities.LiaisonBankily;

public interface LiaisonBankilyRepository extends JpaRepository<LiaisonBankily, Long>{
	@Query("select g from LiaisonBankily g where g.isApproved=false and g.isRejeted=false")
	public List<LiaisonBankily> getAllLiaisonBankily();
	@Query("select g from LiaisonBankily g where g.isApproved=false and g.isRejeted=false and g.idGroupe=?1")
	public List<LiaisonBankily> getAllLiaisonBankilyByIdGroup(long idG);
	@Query("select g from LiaisonBankily g where g.isRejeted=false and g.telephone=?1")
	public LiaisonBankily getLiaisonBankilyByTelephone(String telephone);
	@Query("select g from LiaisonBankily g where g.isRejeted=false and g.cif=?1")
	public LiaisonBankily getLiaisonBankilyByCif(String cif);
}

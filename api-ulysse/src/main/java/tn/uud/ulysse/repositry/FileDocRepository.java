package tn.uud.ulysse.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.uud.ulysse.models.FileDoc;

public interface FileDocRepository extends JpaRepository<FileDoc, Long> {
	public FileDoc getFileDocById( Long id );
}

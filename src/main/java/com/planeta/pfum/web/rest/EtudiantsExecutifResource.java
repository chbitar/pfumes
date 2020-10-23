package com.planeta.pfum.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planeta.pfum.domain.EtudiantsExecutif;
import com.planeta.pfum.repository.EtudiantsExecutifRepository;
import com.planeta.pfum.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.planeta.pfum.domain.EtudiantsExecutif}.
 */
@RestController
@RequestMapping("/api")
public class EtudiantsExecutifResource {

    private final Logger log = LoggerFactory.getLogger(EtudiantsExecutifResource.class);

    private static final String ENTITY_NAME = "etudiantsExecutif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtudiantsExecutifRepository etudiantsExecutifRepository;


    public EtudiantsExecutifResource(EtudiantsExecutifRepository etudiantsExecutifRepository) {
        this.etudiantsExecutifRepository = etudiantsExecutifRepository;
    }

    /**
     * {@code POST  /etudiants-executifs} : Create a new etudiantsExecutif.
     *
     * @param etudiantsExecutif the etudiantsExecutif to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etudiantsExecutif, or with status {@code 400 (Bad Request)} if the etudiantsExecutif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etudiants-executifs")
    public ResponseEntity<EtudiantsExecutif> createEtudiantsExecutif(@Valid @RequestBody EtudiantsExecutif etudiantsExecutif) throws URISyntaxException {
        log.debug("REST request to save EtudiantsExecutif : {}", etudiantsExecutif);
        if (etudiantsExecutif.getId() != null) {
            throw new BadRequestAlertException("A new etudiantsExecutif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtudiantsExecutif result = etudiantsExecutifRepository.save(etudiantsExecutif);
        return ResponseEntity.created(new URI("/api/etudiants-executifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etudiants-executifs} : Updates an existing etudiantsExecutif.
     *
     * @param etudiantsExecutif the etudiantsExecutif to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etudiantsExecutif,
     * or with status {@code 400 (Bad Request)} if the etudiantsExecutif is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etudiantsExecutif couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etudiants-executifs")
    public ResponseEntity<EtudiantsExecutif> updateEtudiantsExecutif(@Valid @RequestBody EtudiantsExecutif etudiantsExecutif) throws URISyntaxException {
        log.debug("REST request to update EtudiantsExecutif : {}", etudiantsExecutif);
        if (etudiantsExecutif.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtudiantsExecutif result = etudiantsExecutifRepository.save(etudiantsExecutif);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etudiantsExecutif.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etudiants-executifs} : get all the etudiantsExecutifs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etudiantsExecutifs in body.
     */
    @GetMapping("/etudiants-executifs")
    public List<EtudiantsExecutif> getAllEtudiantsExecutifs() {
        log.debug("REST request to get all EtudiantsExecutifs");
        return etudiantsExecutifRepository.findAll();
    }

    /**
     * {@code GET  /etudiants-executifs/:id} : get the "id" etudiantsExecutif.
     *
     * @param id the id of the etudiantsExecutif to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etudiantsExecutif, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etudiants-executifs/{id}")
    public ResponseEntity<EtudiantsExecutif> getEtudiantsExecutif(@PathVariable Long id) {
        log.debug("REST request to get EtudiantsExecutif : {}", id);
        Optional<EtudiantsExecutif> etudiantsExecutif = etudiantsExecutifRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(etudiantsExecutif);
    }

    /**
     * {@code DELETE  /etudiants-executifs/:id} : delete the "id" etudiantsExecutif.
     *
     * @param id the id of the etudiantsExecutif to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etudiants-executifs/{id}")
    public ResponseEntity<Void> deleteEtudiantsExecutif(@PathVariable Long id) {
        log.debug("REST request to delete EtudiantsExecutif : {}", id);
        etudiantsExecutifRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}

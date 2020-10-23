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

import com.planeta.pfum.domain.EtudiantsLicence;
import com.planeta.pfum.repository.EtudiantsLicenceRepository;
import com.planeta.pfum.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.planeta.pfum.domain.EtudiantsLicence}.
 */
@RestController
@RequestMapping("/api")
public class EtudiantsLicenceResource {

    private final Logger log = LoggerFactory.getLogger(EtudiantsLicenceResource.class);

    private static final String ENTITY_NAME = "etudiantsLicence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtudiantsLicenceRepository etudiantsLicenceRepository;


    public EtudiantsLicenceResource(EtudiantsLicenceRepository etudiantsLicenceRepository) {
        this.etudiantsLicenceRepository = etudiantsLicenceRepository;
    }

    /**
     * {@code POST  /etudiants-licences} : Create a new etudiantsLicence.
     *
     * @param etudiantsLicence the etudiantsLicence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etudiantsLicence, or with status {@code 400 (Bad Request)} if the etudiantsLicence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etudiants-licences")
    public ResponseEntity<EtudiantsLicence> createEtudiantsLicence(@Valid @RequestBody EtudiantsLicence etudiantsLicence) throws URISyntaxException {
        log.debug("REST request to save EtudiantsLicence : {}", etudiantsLicence);
        if (etudiantsLicence.getId() != null) {
            throw new BadRequestAlertException("A new etudiantsLicence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtudiantsLicence result = etudiantsLicenceRepository.save(etudiantsLicence);
        return ResponseEntity.created(new URI("/api/etudiants-licences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etudiants-licences} : Updates an existing etudiantsLicence.
     *
     * @param etudiantsLicence the etudiantsLicence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etudiantsLicence,
     * or with status {@code 400 (Bad Request)} if the etudiantsLicence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etudiantsLicence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etudiants-licences")
    public ResponseEntity<EtudiantsLicence> updateEtudiantsLicence(@Valid @RequestBody EtudiantsLicence etudiantsLicence) throws URISyntaxException {
        log.debug("REST request to update EtudiantsLicence : {}", etudiantsLicence);
        if (etudiantsLicence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtudiantsLicence result = etudiantsLicenceRepository.save(etudiantsLicence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etudiantsLicence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etudiants-licences} : get all the etudiantsLicences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etudiantsLicences in body.
     */
    @GetMapping("/etudiants-licences")
    public List<EtudiantsLicence> getAllEtudiantsLicences() {
        log.debug("REST request to get all EtudiantsLicences");
        return etudiantsLicenceRepository.findAll();
    }

    /**
     * {@code GET  /etudiants-licences/:id} : get the "id" etudiantsLicence.
     *
     * @param id the id of the etudiantsLicence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etudiantsLicence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etudiants-licences/{id}")
    public ResponseEntity<EtudiantsLicence> getEtudiantsLicence(@PathVariable Long id) {
        log.debug("REST request to get EtudiantsLicence : {}", id);
        Optional<EtudiantsLicence> etudiantsLicence = etudiantsLicenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(etudiantsLicence);
    }

    /**
     * {@code DELETE  /etudiants-licences/:id} : delete the "id" etudiantsLicence.
     *
     * @param id the id of the etudiantsLicence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etudiants-licences/{id}")
    public ResponseEntity<Void> deleteEtudiantsLicence(@PathVariable Long id) {
        log.debug("REST request to delete EtudiantsLicence : {}", id);
        etudiantsLicenceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}

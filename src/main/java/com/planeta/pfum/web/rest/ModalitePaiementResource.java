package com.planeta.pfum.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import com.planeta.pfum.domain.ModalitePaiement;
import com.planeta.pfum.repository.ModalitePaiementRepository;
import com.planeta.pfum.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.planeta.pfum.domain.ModalitePaiement}.
 */
@RestController
@RequestMapping("/api")
public class ModalitePaiementResource {

    private final Logger log = LoggerFactory.getLogger(ModalitePaiementResource.class);

    private static final String ENTITY_NAME = "modalitePaiement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModalitePaiementRepository modalitePaiementRepository;


    public ModalitePaiementResource(ModalitePaiementRepository modalitePaiementRepository) {
        this.modalitePaiementRepository = modalitePaiementRepository;
    }

    /**
     * {@code POST  /modalite-paiements} : Create a new modalitePaiement.
     *
     * @param modalitePaiement the modalitePaiement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modalitePaiement, or with status {@code 400 (Bad Request)} if the modalitePaiement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modalite-paiements")
    public ResponseEntity<ModalitePaiement> createModalitePaiement(@RequestBody ModalitePaiement modalitePaiement) throws URISyntaxException {
        log.debug("REST request to save ModalitePaiement : {}", modalitePaiement);
        if (modalitePaiement.getId() != null) {
            throw new BadRequestAlertException("A new modalitePaiement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModalitePaiement result = modalitePaiementRepository.save(modalitePaiement);
        return ResponseEntity.created(new URI("/api/modalite-paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modalite-paiements} : Updates an existing modalitePaiement.
     *
     * @param modalitePaiement the modalitePaiement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modalitePaiement,
     * or with status {@code 400 (Bad Request)} if the modalitePaiement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modalitePaiement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modalite-paiements")
    public ResponseEntity<ModalitePaiement> updateModalitePaiement(@RequestBody ModalitePaiement modalitePaiement) throws URISyntaxException {
        log.debug("REST request to update ModalitePaiement : {}", modalitePaiement);
        if (modalitePaiement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModalitePaiement result = modalitePaiementRepository.save(modalitePaiement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modalitePaiement.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /modalite-paiements} : get all the modalitePaiements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modalitePaiements in body.
     */
    @GetMapping("/modalite-paiements")
    public List<ModalitePaiement> getAllModalitePaiements() {
        log.debug("REST request to get all ModalitePaiements");
        return modalitePaiementRepository.findAll();
    }

    /**
     * {@code GET  /modalite-paiements/:id} : get the "id" modalitePaiement.
     *
     * @param id the id of the modalitePaiement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modalitePaiement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modalite-paiements/{id}")
    public ResponseEntity<ModalitePaiement> getModalitePaiement(@PathVariable Long id) {
        log.debug("REST request to get ModalitePaiement : {}", id);
        Optional<ModalitePaiement> modalitePaiement = modalitePaiementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(modalitePaiement);
    }

    /**
     * {@code DELETE  /modalite-paiements/:id} : delete the "id" modalitePaiement.
     *
     * @param id the id of the modalitePaiement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modalite-paiements/{id}")
    public ResponseEntity<Void> deleteModalitePaiement(@PathVariable Long id) {
        log.debug("REST request to delete ModalitePaiement : {}", id);
        modalitePaiementRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}

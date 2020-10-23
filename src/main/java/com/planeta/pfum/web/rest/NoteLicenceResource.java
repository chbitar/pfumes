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

import com.planeta.pfum.domain.NoteLicence;
import com.planeta.pfum.repository.NoteLicenceRepository;
import com.planeta.pfum.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.planeta.pfum.domain.NoteLicence}.
 */
@RestController
@RequestMapping("/api")
public class NoteLicenceResource {

    private final Logger log = LoggerFactory.getLogger(NoteLicenceResource.class);

    private static final String ENTITY_NAME = "noteLicence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NoteLicenceRepository noteLicenceRepository;


    public NoteLicenceResource(NoteLicenceRepository noteLicenceRepository ) {
        this.noteLicenceRepository = noteLicenceRepository;
    }

    /**
     * {@code POST  /note-licences} : Create a new noteLicence.
     *
     * @param noteLicence the noteLicence to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new noteLicence, or with status {@code 400 (Bad Request)} if the noteLicence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/note-licences")
    public ResponseEntity<NoteLicence> createNoteLicence(@RequestBody NoteLicence noteLicence) throws URISyntaxException {
        log.debug("REST request to save NoteLicence : {}", noteLicence);
        if (noteLicence.getId() != null) {
            throw new BadRequestAlertException("A new noteLicence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NoteLicence result = noteLicenceRepository.save(noteLicence);
        return ResponseEntity.created(new URI("/api/note-licences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /note-licences} : Updates an existing noteLicence.
     *
     * @param noteLicence the noteLicence to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated noteLicence,
     * or with status {@code 400 (Bad Request)} if the noteLicence is not valid,
     * or with status {@code 500 (Internal Server Error)} if the noteLicence couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/note-licences")
    public ResponseEntity<NoteLicence> updateNoteLicence(@RequestBody NoteLicence noteLicence) throws URISyntaxException {
        log.debug("REST request to update NoteLicence : {}", noteLicence);
        if (noteLicence.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NoteLicence result = noteLicenceRepository.save(noteLicence);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, noteLicence.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /note-licences} : get all the noteLicences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of noteLicences in body.
     */
    @GetMapping("/note-licences")
    public List<NoteLicence> getAllNoteLicences() {
        log.debug("REST request to get all NoteLicences");
        return noteLicenceRepository.findAll();
    }

    /**
     * {@code GET  /note-licences/:id} : get the "id" noteLicence.
     *
     * @param id the id of the noteLicence to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the noteLicence, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/note-licences/{id}")
    public ResponseEntity<NoteLicence> getNoteLicence(@PathVariable Long id) {
        log.debug("REST request to get NoteLicence : {}", id);
        Optional<NoteLicence> noteLicence = noteLicenceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(noteLicence);
    }

    /**
     * {@code DELETE  /note-licences/:id} : delete the "id" noteLicence.
     *
     * @param id the id of the noteLicence to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/note-licences/{id}")
    public ResponseEntity<Void> deleteNoteLicence(@PathVariable Long id) {
        log.debug("REST request to delete NoteLicence : {}", id);
        noteLicenceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}

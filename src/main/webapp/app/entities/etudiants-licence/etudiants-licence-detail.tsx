import axios from 'axios';
import fileDownload from 'react-file-download';
import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import { getEntity, envoyerMail } from './etudiants-licence.reducer';
import { IEtudiantsLicence } from 'app/shared/model/etudiants-licence.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT, AUTHORITIES } from 'app/config/constants';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import { getDocumentByTypeDocument as getDocuments } from 'app/entities/document/document.reducer';

export interface IEtudiantsLicenceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EtudiantsLicenceDetail extends React.Component<IEtudiantsLicenceDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
    this.props.getDocuments('LICENCE');
  }

  genererAttestationInscription = () => () => {
    const requestUrl = `/api/attestation/${this.props.match.params.id}/PDF/LICENCE`;
    axios
      .get(requestUrl, {
        responseType: 'blob'
      })
      .then(res => {
        fileDownload(res.data, 'attestation.pdf');
      });
  };
  genererBadge = () => () => {
    const requestUrl = `/api/badge/etudiant/${this.props.match.params.id}/PDF/LICENCE`;
    axios
      .get(requestUrl, {
        responseType: 'blob'
      })
      .then(res => {
        fileDownload(res.data, 'badge.pdf');
      });
  };

  handleEnvoyerMail = (event, values) => {
    this.props.envoyerMail(values.objet, values.sujet);
  };

  render() {
    const { etudiantsLicenceEntity, isUser, isRespFin, isAdmin, documentList } = this.props;

    return (
      <div>
        <Row>
          <div>
            <Row>
              <Col md="6">
                <span className="badge badge-warning">Informations personnelles</span>
                <h2>
                  N° Etudiant : [<b>{etudiantsLicenceEntity.suffixe}</b>]
                </h2>
                <dl className="jh-entity-details">
                  <dt>
                    <span id="nom">
                      <Translate contentKey="pfumv10App.etudiantsLicence.nom">Nom</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.nom}</dd>
                  <dt>
                    <span id="prenom">
                      <Translate contentKey="pfumv10App.etudiantsLicence.prenom">Prenom</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.prenom}</dd>
                  <dt>
                    <span id="dateNaissance">
                      <Translate contentKey="pfumv10App.etudiantsLicence.dateNaissance">Date Naissance</Translate>
                    </span>
                  </dt>
                  <dd>
                    <TextFormat value={etudiantsLicenceEntity.dateNaissance} type="date" format={APP_DATE_FORMAT} />
                  </dd>
                  <dt>
                    <span id="adresseContact">
                      <Translate contentKey="pfumv10App.etudiantsLicence.adresseContact">Adresse Contact</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.adresseContact}</dd>
                  <dt>
                    <span id="ville">
                      <Translate contentKey="pfumv10App.etudiantsLicence.ville">Ville</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.ville}</dd>
                  <dt>
                    <span id="email">
                      <Translate contentKey="pfumv10App.etudiantsLicence.email">Email</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.email}</dd>
                  <dt>
                    <span id="cinPass">
                      <Translate contentKey="pfumv10App.etudiantsLicence.cinPass">Cin Pass</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.cinPass}</dd>
                  <dt>
                    <span id="codepostal">
                      <Translate contentKey="pfumv10App.etudiantsLicence.codepostal">Codepostal</Translate>
                    </span>
                  </dt>
                  <dd>{etudiantsLicenceEntity.codepostal}</dd>
                </dl>
              </Col>
              <Col md="6">
                <span className="badge badge-warning">Informations filiére</span>
                <dt>
                  <span id="photo">
                    <Translate contentKey="pfumv10App.etudiantsLicence.photo">Photo</Translate>
                  </span>
                </dt>
                <dd>
                  {etudiantsLicenceEntity.photo ? (
                    <div>
                      <a onClick={openFile(etudiantsLicenceEntity.photoContentType, etudiantsLicenceEntity.photo)}>
                        <img
                          src={`data:${etudiantsLicenceEntity.photoContentType};base64,${etudiantsLicenceEntity.photo}`}
                          style={{ maxHeight: '200px' }}
                        />
                      </a>
                    </div>
                  ) : null}
                </dd>
                <dt>
                  <span id="pjBac">Intitulé d'un bac :</span>
                </dt>
                <dd>{etudiantsLicenceEntity.pjBac}</dd>
                <dt>
                  <span id="mention">
                    <Translate contentKey="pfumv10App.etudiantsLicence.mention">Mention</Translate>
                  </span>
                </dt>
                <dd>{etudiantsLicenceEntity.mention}</dd>
                <dt>
                  <Translate contentKey="pfumv10App.etudiantsLicence.filiere">Filiere</Translate>
                </dt>
                <dt>
                  <span id="anneOtention">
                    <Translate contentKey="pfumv10App.etudiantsLicence.anneOtention">Année d'otention</Translate>
                  </span>
                </dt>
                <dd>{etudiantsLicenceEntity.anneeObtention}</dd>
                <dd>{etudiantsLicenceEntity.filiere ? etudiantsLicenceEntity.filiere.nomfiliere : ''}</dd>
                <dt>
                  <span id="paysResidence">
                    <Translate contentKey="pfumv10App.etudiantsLicence.paysResidence">Pays Residence</Translate>
                  </span>
                </dt>
                <dd>{etudiantsLicenceEntity.paysResidence}</dd>

                <dt>
                  <span id="province">
                    <Translate contentKey="pfumv10App.etudiantsLicence.province">Province</Translate>
                  </span>
                </dt>
                <dd>{etudiantsLicenceEntity.province}</dd>
                <dt>
                  <span id="tel">
                    <Translate contentKey="pfumv10App.etudiantsLicence.tel">Tel</Translate>
                  </span>
                </dt>
                <dd>{etudiantsLicenceEntity.tel}</dd>
                <dt>
                  <span id="deuxiemeTel">
                    <Translate contentKey="pfumv10App.etudiantsLicence.deuxiemeTel">Deuxieme Tel</Translate>
                  </span>
                </dt>
                <dd>{etudiantsLicenceEntity.deuxiemeTel}</dd>
              </Col>
            </Row>
          </div>
          <div>
            <Row>
              <Col>
                <span className="badge badge-warning">Piéces jointes</span>

                <dt>
                  <span id="bacalaureat">
                    <Translate contentKey="pfumv10App.etudiantsLicence.bacalaureat">Bacalaureat</Translate>
                  </span>
                </dt>
                <dd>
                  {etudiantsLicenceEntity.bacalaureat ? (
                    <div>
                      <a onClick={openFile(etudiantsLicenceEntity.bacalaureatContentType, etudiantsLicenceEntity.bacalaureat)}>
                        <img
                          src={`data:${etudiantsLicenceEntity.bacalaureatContentType};base64,${etudiantsLicenceEntity.bacalaureat}`}
                          style={{ maxHeight: '30px' }}
                        />
                      </a>
                      <span>
                        {etudiantsLicenceEntity.bacalaureatContentType}, {byteSize(etudiantsLicenceEntity.bacalaureat)}
                      </span>
                    </div>
                  ) : null}
                </dd>
                <dt>
                  <span id="testAdmission">Test d'admission</span>
                </dt>
                <dd>
                  {etudiantsLicenceEntity.testAdmission ? (
                    <div>
                      <a onClick={openFile(etudiantsLicenceEntity.testAdmissionContentType, etudiantsLicenceEntity.testAdmission)}>
                        <img
                          src={`data:${etudiantsLicenceEntity.testAdmissionContentType};base64,${etudiantsLicenceEntity.testAdmission}`}
                          style={{ maxHeight: '30px' }}
                        />
                      </a>
                      <span>
                        {etudiantsLicenceEntity.testAdmissionContentType}, {byteSize(etudiantsLicenceEntity.testAdmission)}
                      </span>
                    </div>
                  ) : null}
                </dd>
                <dt>
                  <span id="relevesNotes">Relevé des Notes</span>
                </dt>
                <dd>
                  {etudiantsLicenceEntity.relevesNotes ? (
                    <div>
                      <a onClick={openFile(etudiantsLicenceEntity.relevesNotesContentType, etudiantsLicenceEntity.relevesNotes)}>
                        <img
                          src={`data:${etudiantsLicenceEntity.relevesNotesContentType};base64,${etudiantsLicenceEntity.relevesNotes}`}
                          style={{ maxHeight: '30px' }}
                        />
                      </a>
                      <span>
                        {etudiantsLicenceEntity.relevesNotesContentType}, {byteSize(etudiantsLicenceEntity.relevesNotes)}
                      </span>
                    </div>
                  ) : null}
                </dd>
                <dt>
                  <span id="cinPassport">
                    <Translate contentKey="pfumv10App.etudiantsLicence.cinPassport">Cin Passport</Translate>
                  </span>
                </dt>
                <dd>
                  {etudiantsLicenceEntity.cinPassport ? (
                    <div>
                      <a onClick={openFile(etudiantsLicenceEntity.cinPassportContentType, etudiantsLicenceEntity.cinPassport)}>
                        <img
                          src={`data:${etudiantsLicenceEntity.cinPassportContentType};base64,${etudiantsLicenceEntity.cinPassport}`}
                          style={{ maxHeight: '30px' }}
                        />
                      </a>
                      <span>
                        {etudiantsLicenceEntity.cinPassportContentType}, {byteSize(etudiantsLicenceEntity.cinPassport)}
                      </span>
                    </div>
                  ) : null}
                </dd>
              </Col>
            </Row>
          </div>
          <div>
            <Row>
              <Col>
                <span className="badge badge-warning">Status d'inscription</span>
                <dt>
                  <span id="inscriptionvalide">
                    <Translate contentKey="pfumv10App.etudiantsLicence.inscriptionvalide">Inscriptionvalide</Translate>
                  </span>
                </dt>
                <dd>
                  {etudiantsLicenceEntity.inscriptionvalide ? (
                    <Button color="success">Validé</Button>
                  ) : (
                    <Button color="danger">En attente</Button>
                  )}
                </dd>
                <dt>
                  <Translate contentKey="pfumv10App.etudiantsLicence.modalite">Modalite</Translate>
                </dt>
                <dd>{etudiantsLicenceEntity.modalite ? etudiantsLicenceEntity.modalite.modalite : ''}</dd>
                {(isAdmin || isUser) && (
                  <dd>
                    <Button color="info" onClick={this.genererAttestationInscription()}>
                      Attestation d'inscription{' '}
                    </Button>
                  </dd>
                )}
                {(isAdmin || isUser) && (
                  <dd>
                    <Button color="info" onClick={this.genererBadge()}>
                      Badge
                    </Button>
                  </dd>
                )}
                <br />
                <br />
                <Button tag={Link} to="/entity/etudiants-licence" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />{' '}
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                {(isAdmin || isUser) && (
                  <Button tag={Link} to={`/entity/etudiants-licence/${etudiantsLicenceEntity.id}/edit`} replace color="primary">
                    <FontAwesomeIcon icon="pencil-alt" />{' '}
                    <span className="d-none d-md-inline">
                      <Translate contentKey="entity.action.edit">Edit</Translate>
                    </span>
                  </Button>
                )}
              </Col>

              <Col md="6">
                <span className="badge badge-warning">Emploi de temps et Avis</span>

                {documentList &&
                  documentList.length > 0 &&
                  documentList.map((document, i) => (
                    <>
                      <dt>
                        <span id={document.titre} />
                      </dt>
                      <dd>
                        <div key={`entity-${i}`}>
                          {document.data ? (
                            <div>
                              <a onClick={openFile(document.dataContentType, document.data)}>
                                <FontAwesomeIcon icon="file-pdf" />
                                {document.titre}
                                &nbsp;
                              </a>
                              <span id={document.titre}>
                                {document.dataContentType}, {byteSize(document.data)}
                              </span>
                            </div>
                          ) : null}
                        </div>
                      </dd>
                    </>
                  ))}
              </Col>
            </Row>
          </div>
          <div>
            <Row>
              <Col>
                <dt />
                <dd>
                  <span className="badge badge-warning">Envoi Demande</span>

                  <div className="card border-primary">
                    <div className="card-header">Envoyer un E-mail</div>
                    <div className="card-body">
                      <AvForm onValidSubmit={this.handleEnvoyerMail}>
                        <AvGroup>
                          <Label id="prenomLabel" for="etudiants-licence-prenom">
                            Object :
                          </Label>
                          <AvField
                            id="etudiants-licence-prenom"
                            type="text"
                            name="objet"
                            validate={{
                              required: { value: true, errorMessage: translate('entity.validation.required') }
                            }}
                          />
                        </AvGroup>
                        <AvGroup>
                          <Label id="observationsLabel" for="suivi-module-observations">
                            Sujet :{' '}
                          </Label>
                          <AvInput
                            id="suivi-module-observations"
                            type="textarea"
                            name="sujet"
                            validate={{
                              required: { value: true, errorMessage: translate('entity.validation.required') }
                            }}
                          />
                        </AvGroup>
                        <Button color="success" type="submit">
                          <Translate contentKey="password.form.button">Save</Translate>
                        </Button>
                      </AvForm>
                    </div>
                  </div>
                </dd>
              </Col>
            </Row>
          </div>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ etudiantsLicence, authentication, document }: IRootState) => ({
  etudiantsLicenceEntity: etudiantsLicence.entity,
  isAdmin: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.ADMIN]),
  isUser: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.USER]),
  isRespFin: hasAnyAuthority(authentication.account.authorities, [AUTHORITIES.ROLE_RESP_FINANCE]),
  documentList: document.entities
});

const mapDispatchToProps = { getEntity, envoyerMail, getDocuments };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtudiantsLicenceDetail);

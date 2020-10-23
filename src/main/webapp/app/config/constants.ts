const config = {
  VERSION: process.env.VERSION
};

export default config;

export const SERVER_API_URL = process.env.SERVER_API_URL;

export const AUTHORITIES = {
  ADMIN: 'ROLE_ADMIN',
  USER: 'ROLE_USER',
  PROF: 'ROLE_PROF',
  ROLE_RESP_FINANCE: 'ROLE_RESP_FINANCE',
  ROLE_ETUDIANT_EXECUTIF: 'ROLE_ETUDIANT_EXECUTIF',
  ROLE_ETUDIANT_LICENCE: 'ROLE_ETUDIANT_LICENCE',
  ROLE_ETUDIANT_MASTER: 'ROLE_ETUDIANT_MASTER',
  ROLE_RESP_FILIERE: 'ROLE_RESP_FILIERE'
};

export const messages = {
  DATA_ERROR_ALERT: 'Internal Error'
};

export const APP_DATE_FORMAT_TIMESTAMP = 'DD/MM/YY HH:mm';
export const APP_DATE_FORMAT = 'DD/MM/YYYY';
export const APP_TIMESTAMP_FORMAT = 'DD/MM/YY HH:mm:ss';
export const APP_LOCAL_DATE_FORMAT = 'DD/MM/YYYY';
/* export const APP_LOCAL_DATETIME_FORMAT = 'YYYY-MM-DDTHH:mm';
 */ export const APP_LOCAL_DATETIME_FORMAT = 'YYYY-MM-DD';
export const APP_LOCAL_DATETIME_FORMAT_Z = 'YYYY-MM-DDTHH:mm Z';
export const APP_WHOLE_NUMBER_FORMAT = '0,0';
export const APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT = '0,0.[00]';
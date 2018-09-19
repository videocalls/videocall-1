/**
 * Created by nori on 2016. 1. 14..
 */
(function (window, vest, undefined) {
    'use strict';
    
    var type = {
        'ko': 0,
        'ko-kr': 0,
        'en-us': 1,
        'ja': 2,
        'ja-jp': 2
    };

    var language = [
        {
            ServiceError_UNKNOWN: "아직 등록되지 않은 오류코드",
            ServiceError_SERVICE_REJECTED: "올바른 MangoWire 메시지가 아니므로, 서비스가 거절되었습니다.",
            ServiceError_MEMORY_ALLOCATION_FAILED: "메모리 할당에 실패했습니다.",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "지원하는 언어가 아닙니다.",

            ServiceError_TOKEN_NOT_INITIALIZED: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_TOKEN_NOT_FOUND: "보안디스크가 존재하지 않습니다.",
            ServiceError_TOKEN_BAD: "보안디스크의 상태가 비정상입니다. 초기화 하세요.",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "Ubikey가 설치되지 않았습니다. 프로그램을 설치 해주세요.",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "Ubikey가 최신 버전이 아닙니다. 프로그램을 업데이트 해주세요.",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikey 옵션값이 잘못되었습니다.",
            ServiceError_TOKEN_NOT_RECOGNIZED: "허용된 토큰이 아닙니다.",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "토큰에서 해당기능을 지원하지 않습니다.",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSL 서비스 초기화에 실패했습니다.",

            ServiceError_SESSIONID_NOT_EXIST: "세션이 만료되었거나 잘못되었습니다. 다시 접속하세요.",
            ServiceError_SESSION_IS_USING: "다른 곳에서 세션이 사용중입니다. 다시 접속하세요.",

            ServiceError_OPERATION_NOT_EXPECTED: "현재 이 기능을 수행할 수 없습니다.",
            ServiceError_OPERATION_NOT_SUPPORTED: "지원되지 않는 기능입니다.",

            ServiceError_INVALID_INPUT: "입력값이 잘못되었습니다.",
            ServiceError_INVALID_INPUT_TOKENID: "토큰 식별자가 잘못되었습니다.",

            ServiceError_NO_SSL_CERTIFICATE: "등록된 SSL 인증서가 존재하지 않습니다.",
            ServiceError_CERTIFICATE_NOT_FOUND: "인증서가 존재하지 않습니다.",


            ServiceError_DELETE_CERTIFICATE_FAILED: "인증서 삭제에 실패했습니다.(기타 에러)",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "Program files에 저장된 인증서는 삭제할 수 없습니다.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PASSWORD_INCORRECT: "인증서 삭제에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_DELETE_PIN_INCORRECT: "인증서 삭제에 실패했습니다(PIN 번호를 확인하세요).",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "인증서 삭제에 실패했습니다(PIN 번호 입력을 취소했습니다).",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "인증서 삭제에 실패했습니다(비밀번호 입력을 취소했습니다).",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom failed.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen key fail",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "키쌍 생성이 사용자에 의해 취소되었습니다.",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "PIN 번호가 잘못되어 키쌍 생성에 실패하였습니다.",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "PIN 번호 오류로 장치가 잠겼습니다. 키쌍 생성에 실패하였습니다.",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "비밀번호가 잘못되어 키쌍 생성에 실패하였습니다.",

            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_GENERATE_SIGNATURE_FAILED: "전자서명에 실패하였습니다.",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "인증서 비밀번호 입력이 잘못 되었습니다.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "전자서명에 실패하였습니다(장치가 잠겼습니다).",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "전자서명에 실패하였습니다(식별번호 생성에 실패).", //"encrypt VID Random failed(0x%x, false)"
            //ServiceError_GENERATE_SIGNATURE_CANCELED: "비밀번호 입력이 사용자에 의해 취소 되었습니다.",
            ServiceError_GENERATE_SIGNATURE_CANCELED: "요청된 작업이 사용자에 의해 취소되었습니다.",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "PIN 번호 입력이 잘못되었습니다.",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom 전자서명에 인증서가 필요합니다.",

            ServiceError_GET_CERTIFICATE_LIST_FAILED: "Function Failed",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKey 서비스가 초기화 되지 않았습니다.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "PIN 번호를 확인하세요.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "PIN 입력을 취소했습니다.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "비밀번호를 확인하세요.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "Ubikey 서비스를 취소했습니다.",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "입력값이 잘못되었습니다.",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "인증서 가져오기를 실패하였습니다.",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "인증서를 찾을 수 없습니다.",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "잘못된 session ID가 입력되었습니다.",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "session ID가 NULL로 입력되었습니다.",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "session 생성에 실패했습니다.",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "비밀번호 입력을 취소했습니다.",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "입력값이 잘못되었습니다.",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_PUSH_CERTIFICATE_FAILED: "PushCertificate failed.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "인증서 비밀번호 확인에 실패했습니다.(기타 에러)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "전자서명에 실패하였습니다(비밀번호를 확인하세요).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "전자서명에 실패하였습니다(장치가 잠겼습니다).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "전자서명에 실패하였습니다(장치가 잠겼습니다).",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "메모리 할당에 실패했습니다.",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "CA 서버와의 통신에 실패했습니다.",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "인증서 발급에 대한 입력값이 잘못 되었습니다.",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "지원되지 않는 인증 기관 코드가 입력되었습니다.",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "PKCS#5 암호화에 실패했습니다.",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 메시지 구성에 실패했습니다.",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "인증서 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "발급된 인증서 저장에 실패했습니다.(initialize 실패)",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "발급된 전자서명용 인증서 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "발급된 키분배용 인증서 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "CA 공개키 저장에 실패했습니다.",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "발급된 인증서 저장에 실패했습니다.(finalize 실패)",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "과금 발급은 현재 지원되지 않습니다.",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "ICCard가 지원하지 않는 인증서입니다.",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "인증서 갱신에 대한 입력값이 잘못 되었습니다.",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "지원되지 않는 인증 기관 코드가 입력되었습니다.",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "갱신할 인증서를 가져오는데 실패했습니다.",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "갱신할 인증서를 추가하는데 실패했습니다.",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "갱신할 키파일을 추가하는데 실패했습니다.",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "PKCS#5 암호화에 실패했습니다.",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 메시지 구성에 실패했습니다.",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "인증서 저장에 실패했습니다.",
            ServiceError_CMP_UPDATE_INVALID_PASSWORD: "이전 인증서 비밀번호를 확인하세요.",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "발급된 인증서 저장에 실패했습니다.(initialize 실패)",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "발급된 전자서명용 인증서 저장에 실패했습니다.",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "발급된 키분배용 인증서 저장에 실패했습니다.",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "CA 공개키 저장에 실패했습니다.",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "발급된 인증서 저장에 실패했습니다.(finalize 실패)",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "과금 갱신은 현재 지원되지 않습니다.",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "인증서를 갱신할 수 있는 기간이 아닙니다. 인증서 갱신은 만료 1개월 전 부터 가능합니다.",
            ServiceError_CMP_UPDATE_INVALID_PIN: "PIN 번호를 확인하세요",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "인증서 폐기에 대한 입력값이 잘못 되었습니다.",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "지원되지 않는 인증 기관 코드가 입력되었습니다.",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "폐기할 인증서를 가져오는데 실패했습니다.",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "폐기할 인증서를 추가하는데 실패했습니다.",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "폐기할 키파일을 추가하는데 실패했습니다.",
            ServiceError_CMP_REVOKE_INVALID_PASSWORD: "비밀번호를 확인하세요.",
            ServiceError_CMP_REVOKE_INVALID_PIN: "PIN 번호를 확인하세요.",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PIN이 잠겼습니다.",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "메모리 할당에 실패했습니다.",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "단말 식별 값을 가져오지 못했습니다(Windows 외 타 OS는 추후 지원합니다).",
            ServiceError_GET_PCIDENTITY_FAILED: "단말 식별 값을 가져오지 못했습니다(기타 에러).",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "비밀번호 변경에 실패했습니다(인증서 형식에 문제가 발생했습니다).",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "비밀번호 변경에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "비밀번호 변경에 실패했습니다(인증서를 저장할 때 문제가 발생했습니다).",
            ServiceError_CHANGE_PIN_FAILED: "비밀번호 변경에 실패하였습니다(기타 에러).",
            ServiceError_CHANGE_PIN_FAILED_PROGRAM_FILES_PATH_WARNING: "Program files에 저장된 인증서 비밀번호는 변경할 수 없습니다.",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "비밀번호 입력을 취소했습니다.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "인증서 내보내기를 취소했습니다.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "인증서 내보내기에 실패했습니다(인증서 형식에 문제가 발생했습니다).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "인증서 내보내기에 실패했습니다(인증서를 찾지 못했습니다).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "인증서 내보내기에 실패했습니다(PIN 번호를 확인하세요).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "인증서 내보내기에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "인증서 내보내기에 실패했습니다(add certificate fail).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "인증서 내보내기에 실패했습니다(encode pfx fail).",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "인증서 내보내기에 실패했습니다(기타 에러).",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "인증서 가져오기에 실패했습니다(인증서 선택을 취소했습니다).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "인증서 가져오기에 실패했습니다(비밀번호 입력을 취소했습니다).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "인증서 가져오기에 실패했습니다(PFX 형식의 인증서가 아닙니다).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PASSWORD: "인증서 가져오기에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "인증서 가져오기에 실패했습니다(기타 에러).",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "비밀번호 확인에 실패했습니다(입력값이 잘못되었습니다).",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "비밀번호 확인에 실패했습니다(입력을 취소했습니다).",
            ServiceError_VERIFY_PIN_FAILED: "비밀번호 확인에 실패했습니다(비밀번호를 확인하세요).",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "입력된 매체는 사용할 수 없는 매체입니다.",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "인증서 저장매체 변경에 실패했습니다(비밀번호 입력을 취소했습니다).",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "인증서 저장매체 변경에 실패했습니다.",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "인증서 저장매체 변경에 실패했습니다(PIN 번호를 확인하세요).",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "인증서 저장매체 변경에 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "변경할 인증서 저장매체가 같습니다.",
            ServiceError_CHANGE_STORAGE_FAILED: "인증서 저장매체 변경에 실패했습니다(기타 에러).",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "입력값이 잘못 되었습니다.",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "인증서 형식이 잘못되었습니다.",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "인증서 검증에 실패했습니다.",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "인증서 유효성 검증에 실패했습니다(기타 에러).",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "session id가 없어 session 저장에 실패했습니다.",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "잘못된 tray 목록입니다.",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "잘못된 tray operate 동작입니다.",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "원문이 필요한 전자서명입니다.",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "아직 지원되지 않는 전자서명입니다.",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "X509 인증서형태가 아닙니다.",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "public key 형태가 아닙니다.",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "서명검증에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "파일 읽기에 실패했습니다.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "파일 쓰기에 실패했습니다.",

            ServiceError_VERIFY_VID_INVALID_CERTID: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_INVALID_KEYID: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_INVALID_IDN: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "보안디스크가 초기화 되지 않았습니다.",
            ServiceError_VERIFY_VID_NOT_FOUND: "입력값이 잘못되었습니다.",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "X509 인증서형태가 아닙니다.",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "random을 가져오는데 실패했습니다.",
            ServiceError_VERIFY_VID_INVALID_PWD: "random을 가져오는데 실패했습니다(비밀번호를 확인하세요).",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "VID 검증에 실패했습니다.",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "message digest를 실패했습니다(입력값이 잘못되었습니다).",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "message digest를 실패했습니다(지원하지 않는 알고리즘입니다).",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "message digest를 실패했습니다(지원하지 않는 알고리즘입니다).",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digest를 실패했습니다(파일을 찾을 수 없습니다).",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digest를 실패했습니다(파일을 읽는데 오류가 발생했습니다).",
            ServiceError_GET_HASH_FAILED: "message digest를 실패했습니다(기타 에러).",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "message 암호화를 실패했습니다(입력값이 잘못되었습니다).",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "message 암호화를 실패했습니다(key 값이 없습니다).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "message 암호화를 실패했습니다(key 길이가 잘못되었습니다).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "message 암호화를 실패했습니다(지원하지 않는 암호화 알고리즘입니다).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "message 암호화를 실패했습니다(지원하지 않는 운영모드입니다).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_PADDING: "message 암호화를 실패했습니다(지원하지 않는 패딩입니다).",
            ServiceError_ENCRYPT_FAILED: "message 암호화를 실패했습니다(기타에러).",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "message 복호화를 실패했습니다(입력값이 잘못되었습니다).",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "message 복호화를 실패했습니다(key 값이 없습니다).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "message 복호화를 실패했습니다(key 길이가 잘못되었습니다).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "message 복호화를 실패했습니다(지원하지 않는 복호화 알고리즘입니다).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "message 복호화를 실패했습니다(지원하지 않는 운영모드입니다).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_PADDING: "message 복호화를 실패했습니다(지원하지 않는 패딩입니다).",
            ServiceError_DECRYPT_FAILED: "message 복호화를 실패했습니다(기타에러).",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "전자봉투를 실패했습니다(입력값이 잘못되었습니다).",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "전자봉투를 실패했습니다(입력한 인증서가 x509가 아닙니다).",
            ServiceError_ENVELOPE_FAILED: "전자봉투를 실패했습니다(기타 에러).",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "전자봉투 복호화에 실패했습니다(입력값이 잘못되었습니다).",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "전자봉투 복호화에 실패했습니다(입력을 취소했습니다).",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "전자봉투 복호화에 실패했습니다(PIN 번호를 확인하세요).",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "전자봉투 복호화에 실패했습니다(비밀번호를 취소했습니다).",
            ServiceError_DEENVELOPE_FAILED_FILE_READ: '',
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE: '',
            ServiceError_DEENVELOPE_FAILED: "전자봉투 복호화에 실패했습니다.",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "PFX 전자서명에 실패했습니다(기타 에러).",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "PFX 전자서명에 실패했습니다(선택을 취소했습니다).",


            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "입력값이 잘못 되었습니다.",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "키보드보안 연동 실패했습니다.",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "키보드보안 초기화 실패했습니다.",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "입력값 획득에 실패 했습니다(키보드 보안 연동 오류).",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "키보드보안 공개키 획득에 실패했습니다.",

            /* mobile usim */
            ServiceError_MOBILE_USIM_NOT_PRESENT: "스마트인증 프로그램이 설치되지 않았습니다.",
            ServiceError_MOBILE_USIM_INVALID_OPTIONS: "스마트인증의 옵션이 잘못되었습니다.",
            ServiceError_MOBILE_USIM_USER_CANCELED: "스마트인증이 취소되었습니다.",

            /* relay.raon */
            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "인증서 복사에 실패했습니다(인증번호 요청이 필요합니다).",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "인증서 복사에 실패했습니다(인증번호 요청에 실패했습니다).",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "인증서 복사에 실패했습니다(전송된 인증서가 없습니다).",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "인증서 복사에 실패했습니다(보내는 단말에 인증번호를 입력해주세요).",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "인증서 복사에 실패했습니다(인증서 내보내기에 실패했습니다).",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "인증서 복사에 실패했습니다(인증번호가 일치하지 않습니다.)",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "인증서 복사에 실패했습니다(지원되지 않는 토큰입니다.).",

            /* SCRIPT */
            ScriptError_SETMATCHEDCONTEXT_NOT_CERTIFICATE: "요청하신 인증서가 없습니다.",
            ScriptError_SIGNDATAB64_NOT_PLAINTEXT: "입력 매개변수가 null입니다.",
            ScriptError_CERTSERVICESETUP_VERSION_MODULE: "설치한 모듈 버전이 낮습니다, 최신버전을 설치해주세요,",
            ScriptError_CERTSERVICESETUP_VERSION_DLL: "설치한 DLL 버전이 낮습니다, 최신버전을 설치해주세요,",
            ScriptError_UCPIDREQUSER_NOT_USERAGREEMENT: "입력 매개변수가 null입니다. (userAgreement)",
            ScriptError_UCPIDREQUSER_NOT_MODE: "입력 잘못된 매개변수 입니다. (mode)",
            ScriptError_PFX_MAKESIGNATURE_ERORR: "전자서명 중 에러가 발생하였습니다."
        },
        {
            ServiceError_UNKNOWN: "The error code is not registered yet",
            ServiceError_SERVICE_REJECTED: "Service has bveen rejected becasue of invalid MangoWiore message.",
            ServiceError_MEMORY_ALLOCATION_FAILED: "Memory allocation failed.",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "It is not a supported language.",

            ServiceError_TOKEN_NOT_INITIALIZED: "SecureDisk is not initialized.",
            ServiceError_TOKEN_NOT_FOUND: "SecureDisk does not exist.",
            ServiceError_TOKEN_BAD: "SecureDisk is abnormal, Please initialized first.",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "Ubikey is not installed. Please install the program.",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "Ubikey is not the latest version. Please update your program.",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikey option value is invalid.",
            ServiceError_TOKEN_NOT_RECOGNIZED: "Token not allowed.",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "It does not support that feature in the token.",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSL Service initialization failed.",

            ServiceError_SESSIONID_NOT_EXIST: "The session expires or is invalid. Please connect again.",
            ServiceError_SESSION_IS_USING: "The session is in use elsewhere. Please connect again.",

            ServiceError_OPERATION_NOT_EXPECTED: "Currently, you can not perform this function.",
            ServiceError_OPERATION_NOT_SUPPORTED: "This function is not supported.",

            ServiceError_INVALID_INPUT: "Invalid input.",
            ServiceError_INVALID_INPUT_TOKENID: "The token identifier is invalid.",

            ServiceError_NO_SSL_CERTIFICATE: "Registered SSL certificate does not exist.",
            ServiceError_CERTIFICATE_NOT_FOUND: "Certificate does not exist.",


            ServiceError_DELETE_CERTIFICATE_FAILED: " Failed to delete the certificate (other error)",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "Invalid input.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "Certificate stored in the [Program files] can not be deleted.",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PASSWORD_INCORRECT: "Delete a certificate failed (check password).",
            ServiceError_DELETE_PIN_INCORRECT: "It failed to delete certificate (Make a PIN number).",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "Failed to delete certificate (canceled the PIN number).",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "Failed to delete certificate (canceled the password).",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "Invalid input.",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom failed.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "Invalid input.",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen key fail",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "Generate key pair has been canceled by the user.",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "Failed to generate key pairs password is incorrect.",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "The key-pair generation failed, because the device is locked by wrong password.",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "The password is incorrect Failed to generate key pairs.",

            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "Unexpected keyIdentifier",
            ServiceError_GENERATE_SIGNATURE_FAILED: "It failed to electronic signatures.",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "SecureDisk is not initialized.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "Enter Password of the certificate is incorrect.",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "Failed to electronic signatures (the device is locked.)",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "t failed to electronic signature (VID Random Number generated on failure).", //"encrypt VID Random failed(0x%x, false)"
            //ServiceError_GENERATE_SIGNATURE_CANCELED: "Password input has been canceled by the user.",
            ServiceError_GENERATE_SIGNATURE_CANCELED: "Request Donnie work was canceled by the user.",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "Invalid input.",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "The PIN number entered is invalid.",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom a digital signature certificate is required.",

            ServiceError_GET_CERTIFICATE_LIST_FAILED: "Function Failed",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKey service is not initialized.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "Check out the PIN number.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "It was canceled by entering a PIN.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "I've canceled the password input.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "Check the password.",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "Ubikey canceled the service.",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "Invalid input.",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "Failed to load the certificate.",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "Can not find the certificate.",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "Incorrect session ID was input.",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "The session ID is input as NULL.",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "Failed to create session.",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "Enter the password canceled.",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "SecureDisk is not initialized.",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "Invalid input.",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "Unexpected keyIdentifier",
            ServiceError_PUSH_CERTIFICATE_FAILED: "PushCertificate failed.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "SecureDisk is not initialized.",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "Failed password verification certificate (other error)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "Invalid input.",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "Failure to have an electronic signature (Check password).",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "Failed to electronic signatures (the device is locked.)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "Failed to electronic signatures (the device is locked.)",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "Memory allocation failed.",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "It failed to communicate with the CA server.",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "The input value for the certificates issued were incorrect.",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "Unsupported certificate authority code has been inputted.",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "Input password canceled",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "Failed PKCS#5 encryption.",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "Failed PKCS#8 mssage generation.",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "The certificate store failed.",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "It failed to save the issued certificate. (Initialize failed)",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "Failed issued certificates for signing electronic stores.",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "Failed to save the certificate issued for key distribution.",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "Failed CA public key storage.",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "Failed to save the issued certificate. (Finalize failure)",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "Billing issue is not currently supported.",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "The certificate that is not supported by the IC Card.",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "The input values for the renewal certificate is invalid.",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "Unsupported certificate authority code has been entered.",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "Input the password canceled.",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "Failed to retrieve the certificate to be renewed.",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "Failed to add the certificate to be renewed.",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "Failed to add the key file to be updated.",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "Failed PKCS#5 encryption.",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "Failed PKCS#8 mssage generation.",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "The certificate store failed.",
            ServiceError_CMP_UPDATE_INVALID_PASSWORD: "Check the previous certificate password.",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "It failed to save the issued certificate. (Initialize failed)",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "Failed issued certificates for signing electronic stores.",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "Failed to save the certificate issued for key distribution.",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "Failed CA public key storage.",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "Failed to save the issued certificate. (Finalize failure)",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "Billing issue is not currently supported.",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "Period in which it is possible to update the certificate does not have. Update of the certificate has expired, it is possible from one month ago.",
            ServiceError_CMP_UPDATE_INVALID_PIN: "Please check the PIN number.",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "The input values for the invalid certificate revocation.",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "Unsupported certificate authority code has been entered.",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "Input the password canceled.",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "Failed to retrieve the certificate to be revoked.",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "Failed to add the certificate to be revoked.",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "Failed to add the key file to be revoked.",
            ServiceError_CMP_REVOKE_INVALID_PASSWORD: "Please confirm your password.",
            ServiceError_CMP_REVOKE_INVALID_PIN: "Please check the PIN number.",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PIN is locked.",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "Memory allocation failed.",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "Failed to get the device identification Values (Windows OS is subsequently other outside support).",
            ServiceError_GET_PCIDENTITY_FAILED: "Failed to get the device identification value (other error).",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "Enter the password canceled.",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "The password change failed (There was a problem with the certificate format).",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "Failed to change password (check password).",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "Failed to change password (There was a problem in stroring the certificate",
            ServiceError_CHANGE_PIN_FAILED: "The password change failed (other error).",
            ServiceError_CHANGE_PIN_FAILED_PROGRAM_FILES_PATH_WARNING: "Password for the certificate, which is stored in the Program files can not be changed.",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "Enter the password canceled.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "It has canceled the certificate export.",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "Export Certificate failed (There was a problem with the certificate format).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "Export Certificate failed (Failed to find the certificate).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "Export Certificate failed (check password).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "Exporting the certificate failed (Check the password).",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "Export Certificate failed (add certificate fail). ",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "Export Certificate failed (encode pfx fail).",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "Export Certificate failed(other error).",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "The Certificate Import failed (canceled the certificate selection).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "The Certificate Import failed (canceled the password input).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "Failed to import the certificate (not certificate of PFX format).",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PASSWORD: "The Certificate Import failed (check password).",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "The Certificate Import failed (other error).",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "Failed to verify passwords (Invalid input).",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "Failed to verify passwords (the input has been canceled).",
            ServiceError_VERIFY_PIN_FAILED: "Failed password verification (Please confirm your password).",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "The input storage is not available.",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "Failed to change the certificate storage (canceled the password input).",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "Failed to change the certificate storage media.",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "Failed to change the certificate storage (check password).",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "It failed to change the certificate storage medium (Check password).",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "Selected same storage for changing stroage",
            ServiceError_CHANGE_STORAGE_FAILED: "Failed to change the certificate storage media (other error).",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "Invalid input.",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "This format of certificate is invalid.",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "It failed certificate validation.",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "Certificate validation failed (other errors).",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "session id is not saved becasue the session ID is null.",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "Invalid tray list.",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "Invalid tray operation.",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "Invalid input.",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "The original digital signature is necessary.",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "The signature type is not supported yet.",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "The certificate is not a form X509.",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "The key is not a form of public key ",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "Signature verification failed.",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "Failed to read the file.",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "Failed to write file.",

            ServiceError_VERIFY_VID_INVALID_CERTID: "Input value is incorrect.",
            ServiceError_VERIFY_VID_INVALID_KEYID: "Input value is incorrect.",
            ServiceError_VERIFY_VID_INVALID_IDN: "Input value is incorrect.",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "Security disk was not initialized.",
            ServiceError_VERIFY_VID_NOT_FOUND: "Input value is incorrect.",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "There is no form of X509 certificate.",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "It failed to bring random.",
            ServiceError_VERIFY_VID_INVALID_PWD: "It failed to bring random（Please check the password）.",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "It failed to VID verification.",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "message digest I failed（Input value was wrong）.",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "message digest I failed（This is an unsupported algorithm）.",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "message digest I failed（This is an unsupported algorithm）.",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digest I failed（File not found）.",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digest I failed（There was an error to read the file）.",
            ServiceError_GET_HASH_FAILED: "message digest I failed（Other error）.",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "It failed in message encryption(Input value is incorrect).",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "It failed in message encryption(t does not have a value of key).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "It failed in message encryption(The length of the key is incorrect).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "It failed in message encryption(This is an unsupported encryption algorithm).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "It failed in message encryption(This is an unsupported operating mode).",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_PADDING: "It failed in message encryption(This is an unsupported padding).",
            ServiceError_ENCRYPT_FAILED: "It failed in message encryption（Other error）.",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "It failed to message decryption(Input value is incorrect).",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "It failed to message decryption(It does not have a value of key).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "It failed to message decryption(The length of the key is incorrect).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "It failed to message decryption(This is an unsupported encryption algorithm).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "It failed to message decryption(This is an unsupported operating mode).",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_PADDING: "It failed to message decryption(This is an unsupported padding).",
            ServiceError_DECRYPT_FAILED: "It failed to message decryption(Other error).",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "It failed the envelope(Invalid input).",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "It failed the envelope(Enter the certificate is not the x509).",
            ServiceError_ENVELOPE_FAILED: "It failed the envelope(Other error).",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "It failed to child envelope decryption(Input value is incorrect).",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "It failed to child envelope decryption(It canceled the input).",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "It failed to child envelope decryption(Please check the PIN number).",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "It failed to child envelope decryption(I canceled the password).",
            ServiceError_DEENVELOPE_FAILED_FILE_READ: '',
            ServiceError_DEENVELOPE_FAILED_FILE_WRITE: '',
            ServiceError_DEENVELOPE_FAILED: "It failed to child envelope decryption.",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "It failed to PFX signature（Other error）.",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "It failed to PFX signature（I was deselected）.",

            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "Invalid input.",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "The keyboard security module operation was failed.",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "The keyboard security module initialization failed",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "Failed to obtain the input values (keyboard interlocking security error)",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "Failed to retrieve Public Key from keyboard security module.",

            ServiceError_MOBILE_USIM_NOT_PRESENT: "Smart certification program was not installed.",
            ServiceError_MOBILE_USIM_INVALID_OPTIONS: "Smart authentication options is incorrect.",
            ServiceError_MOBILE_USIM_USER_CANCELED: "Smart authentication has been canceled.",

            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "Failed to copy of the certificate (authentication number request is required).",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "Copy of the certificate to the failed (failed authentication number request).",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "Copy of the certificate to the failed (no sent certificate).",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "Copy of the certificate to the failed (please enter the authentication number to the transmitting terminal).",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "Copy of the certificate to the failed (and failed to export the certificate).",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "t failed to copy of the certificate (no authentication numbers match).",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "Copy of the certificate to the failed (it is a token that is not supported).",

            /* SCRIPT */
            ScriptError_SETMATCHEDCONTEXT_NOT_CERTIFICATE: "There is no certificate that has been requested.",
            ScriptError_SIGNDATAB64_NOT_PLAINTEXT: "Input parameter is null.",
            ScriptError_CERTSERVICESETUP_VERSION_MODULE: "Version of the installed modules is low. Please install the latest version.",
            ScriptError_CERTSERVICESETUP_VERSION_DLL: "Version of the installed DLL will be low . Please install the latest version.",
            ScriptError_UCPIDREQUSER_NOT_USERAGREEMENT: "Input parameter is null. （userAgreement）",
            ScriptError_UCPIDREQUSER_NOT_MODE: "Input parameter is invalid. （mode）",
            ScriptError_PFX_MAKESIGNATURE_ERORR: "There was an error in an electronic signature."
        },

        {
            ServiceError_UNKNOWN: "未登録エラーコード",
            ServiceError_SERVICE_REJECTED: "MangoWireメッセージが不正のため、サービスを中断します。",
            ServiceError_MEMORY_ALLOCATION_FAILED: "メモリの割り当てに失敗しました。",
            ServiceError_NOT_SUPPORTED_LANGUAGE: "サポートしている言語がありません。",

            ServiceError_TOKEN_NOT_INITIALIZED: "セキュリティディスクが初期化されませんでした。",
            ServiceError_TOKEN_NOT_FOUND: "セキュリティディスクが存在しません。",
            ServiceError_TOKEN_BAD: "セキュリティディスクの状態が異常です。初期化してください。",
            ServiceError_TOKEN_UBIKEY_NOT_INSTALLED: "Ubikeyがインストールされていません。プログラムをインストールしてください。",
            ServiceError_TOKEN_UBIKEY_NOT_LATEST_VERSION: "Ubikeyは最新バージョンではありません。プログラムを更新してください。",
            ServiceError_TOKEN_UBIKEY_INVALID_OPTIONS: "Ubikeyオプションの値が無効です。",
            ServiceError_TOKEN_NOT_RECOGNIZED: "許可されたトークンはありません。",
            ServiceError_TOKEN_FUNCTION_NOT_SUPPORTED: "トークンでその機能をサポートしていません。",

            ServiceError_SSLCONFIG_SERVICE_SSL_INIT_FAILED: "SSLサービスの初期化に失敗しました。",

            ServiceError_SESSIONID_NOT_EXIST: "セッションの有効期間が切れたか、または無効です。再接続してください。",
            ServiceError_SESSION_IS_USING: "他の場所でセッションを使用中です。再接続してください。",

            ServiceError_OPERATION_NOT_EXPECTED: "現在、この機能を実行することはできません。",
            ServiceError_OPERATION_NOT_SUPPORTED: "サポートできない機能です。",

            ServiceError_INVALID_INPUT: "入力した値は正しくありません。",
            ServiceError_INVALID_INPUT_TOKENID: "トークン識別者が正しくありません。",

            ServiceError_NO_SSL_CERTIFICATE: "登録されたSSLが存在しません。",
            ServiceError_CERTIFICATE_NOT_FOUND: "認証書が存在しません。",


            ServiceError_DELETE_CERTIFICATE_FAILED: "認証書の削除に失敗しました。（その他のエラー）",
            ServiceError_DELETE_CERTIFICATE_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PROGRAM_FILES_PATH_DELETE_WARNING: "プログラムファイルに保存された認証書は削除できません。",//"Invalid arugment(certIdentifier)");
            ServiceError_DELETE_PASSWORD_INCORRECT: "認証書の削除に失敗しました。（パスワードを確認してください。）",
            ServiceError_DELETE_PIN_INCORRECT: "認証書の削除に失敗しました（PIN番号を確認してください）。",
            ServiceError_DELETE_PIN_FAILED_INPUT_CANCELED: "認証書の削除に失敗しました（PIN番号の入力をキャンセルしました）。",
            ServiceError_DELETE_PWD_FAILED_INPUT_CANCELED: "認証書の削除に失敗しました（パスワードの入力をキャンセルしました）。",

            ServiceError_ENCRYPT_VIDRANDOM_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",//"Invalid arugment(keyIdentifier or recipientCertificate)");
            ServiceError_ENCRYPT_VIDRANDOM_FAILED: "EncryptVIDRandom failed.",
            ServiceError_ENCRYPT_VIDRANDOM_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",


            ServiceError_GENERATE_KEYPAIR_INVALID_ARGUMENT: "入力した値は正しくありません。",//"Invalid arugment(algorithm or modularLength)");
            ServiceError_GENERATE_KEYPAIR_FAILED: "Gen key fail",
            ServiceError_GENERATE_KEYPAIR_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",
            ServiceError_GENERATE_KEYPAIR_CANCELLED: "秘密鍵・公開鍵の生成がユーザによってキャンセルされました。",
            ServiceError_GENERATE_KEYPAIR_PIN_INCORRECT: "パスワードが正しくありません。秘密鍵・公開鍵の生成に失敗しました。",
            ServiceError_GENERATE_KEYPAIR_PIN_LOCKED: "パスワードがロックされて、秘密鍵・公開鍵の生成に失敗しました。",
            ServiceError_GENERATE_KEYPAIR_PWD_INCORRECT: "パスワードが間違って鍵ペアの生成に失敗しました。",

            ServiceError_GENERATE_SIGNATURE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_GENERATE_SIGNATURE_FAILED: "電子署名に失敗しました。",
            ServiceError_GENERATE_SIGNATURE_TOKEN_NOT_INITAILIZE: "セキュリティディスクが初期化されませんでした。",
            ServiceError_GENERATE_SIGNATURE_FAILED_PWD_INCORRECT: "認証書のパスワード入力が間違っています。",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_INCORRECT: "電子署名に失敗しました。(パスワードを確認してください。).",
            ServiceError_GENERATE_SIGNATURE_FAILED_PIN_LOCKED: "電子署名に失敗しました。(装置がロックされました。)",
            ServiceError_GENERATE_SIGNATURE_FAILED_SGPKCS8_PRIVATEKEYINFO_DECODE_FAILED: "電子署名に失敗しました。(パスワードを確認してください。)",
            ServiceError_GENERATE_SIGNATURE_ENCRYPT_VIDRANDOM_FAILED: "電子署名に失敗しました。(シリアル番号の生成に失敗)", //"encrypt VID Random failed(0x%x, false)"
            //ServiceError_GENERATE_SIGNATURE_CANCELED: "パスワードの入力はユーザによってキャンセルされました。",
            ServiceError_GENERATE_SIGNATURE_CANCELED: "リクエストドニー作業がユーザーによって取り消されました。",
            ServiceError_GENERATE_SIGNATURE_INVALID_ARGUMENT: "入力した値は正しくありません。",//"Invalid input (plain, keyIdentifier, false)",
            ServiceError_GENERATE_SIGNATURE_KSTOKEN_PIN_INCORRECT: "PIN番号の入力が間違っています。",
            ServiceError_GENERATE_SIGNATURE_KOSCOM_SIGN_MUST_HAVE_CERTIFICATE: "Koscom電子署名認証書が必要になります。",

            ServiceError_GET_CERTIFICATE_LIST_FAILED: "Function Failed",
            ServiceError_GET_CERTIFICATE_LIST_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",
            ServiceError_GET_CERTIFICATE_LIST_UBIKEY_NOT_INITIALIZE: "UBIKeyサービスが初期化されていません。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PIN_INCORRECT: "PIN番号を確認してください。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPIN_CANCELED: "PIN入力を取り消しました。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_INPUTPWD_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_PWD_INCORRECT: "パスワードを確認してください。",
            ServiceError_GET_CERTIFICATE_LIST_FAILED_UBIKEY_INPUT_CANCELED: "Ubikeyサービスをキャンセルしました。",

            ServiceError_GET_CERTIFICATE_INVALID_ARGUMENT: "入力した値は正しくありません。",//"certIdentifier"
            ServiceError_GET_CERTIFICATE_FAILED: "認証書のインポートに失敗しました。",
            ServiceError_GET_CERTIFICATE_NOT_FOUND: "認証書を見つかりません。",
            ServiceError_GET_CERTIFICATE_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",

            ServiceError_SETMATCHED_CONTEXT_INVALID_CUSTOM_SID: "入力したセッションIDが正しくありません。",
            ServiceError_SETMATCHED_CONTEXT_CUSTOM_SID_IS_NULL: "セッションIDでNULLを入力しました。",
            ServiceError_SETMATCHED_CONTEXT_CREATE_SESSION_UNIT_FAILED: "セッションの生成に失敗しました。",
            ServiceError_SETMATCHED_CONTEXT_INPUT_CANCELED: "パスワードの入力をキャンセルしました。",

            ServiceError_GET_CA_CERTIFICATE_INVALID_ARGUMENT: "入力した値は正しくありません。",

            ServiceError_PUSH_CERTIFICATE_INVALID_ARGUMENT: "入力した値は正しくありません。",//"Invalid arugment(keyIdentifier or certificate, false)",
            ServiceError_PUSH_CERTIFICATE_NOT_EXPECTED_KEYIDENTIFIER: "not expected keyIdentifier",
            ServiceError_PUSH_CERTIFICATE_FAILED: "PushCertificate failed.",
            ServiceError_PUSH_CERTIFICATE_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されませんでした。",

            ServiceError_VERIFY_CERTIFICATE_FAILED: "認証書パスワードの確認に失敗しました。(その他のエラー)",
            ServiceError_VERIFY_CERTIFICATE_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",

            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_INCORRECT: "電子署名に失敗しました。(パスワードを確認してください。)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_INCORRECT: "電子署名に失敗しました。(パスワードを確認してください。)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_PIN_LOCKED: "電子署名に失敗しました。(装置がロックされました。)",
            ServiceError_GENERATE_SIGNATURE_TOKEN_CERT_PIN_LOCKED: "電子署名に失敗しました。(装置がロックされました。)",

            ServiceError_CMP_MEMORY_ALLOCATION_FAILED: "メモリの割り当てに失敗しました。",
            ServiceError_CMP_SERVER_CONNECT_FAILED: "CAサーバーとの通信に失敗しました。",

            ServiceError_CMP_ISSUE_INVALID_ARGUMENT: "認証書発給時に入力した値は正しくありません。",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_CA: "サポートできない認証機関コードが入力されました。",
            ServiceError_CMP_ISSUE_INPUTPIN_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CMP_ISSUE_PKCS5_ENCRYPT_FAILED: "PKCS#5 暗号化に失敗しました。",
            ServiceError_CMP_ISSUE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 メッセージの生成に失敗しました。",
            ServiceError_CMP_ISSUE_SAVE_CERTIFICATE_FAILED: "認証書の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_INIT_FAILED: "発行された認証書の保存に失敗しました。（initialize失敗）",
            ServiceError_CMP_ISSUE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "発行された電子署名用認証書の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "発行された鍵配布用認証書の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_CA_PUB_IMPORT_FAILED: "CAの公開鍵の保存に失敗しました。",
            ServiceError_CMP_ISSUE_IMPORT_FINAL_FAILED: "発行された認証書の保存に失敗しました。（finalize失敗）",
            ServiceError_CMP_ISSUE_NOT_SUPPORTED_BILL: "有料発行はサポートされていません。",
            ServiceError_CMP_ISSUE_LOW_SPEC_ICCARD: "IC Cardがサポートしていない認証書です。",

            ServiceError_CMP_UPDATE_INVALID_ARGUMENT: "認証書更新時に入力した値は正しくありません。",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_CA: "サポートできない認証機関コードが入力されました。",
            ServiceError_CMP_UPDATE_INPUTPIN_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CMP_UPDATE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "更新する認証書のインポートに失敗しました。",
            ServiceError_CMP_UPDATE_ADD_OLD_CERTIFICATE_FAILED: "更新する認証書の追加に失敗しました。",
            ServiceError_CMP_UPDATE_ADD_OLD_KEY_FAILED: "更新するキーファイルの追加に失敗しました。",
            ServiceError_CMP_UPDATE_PKCS5_ENCRYPT_FAILED: "PKCS#5 暗号化に失敗しました。",
            ServiceError_CMP_UPDATE_MAKE_ENCRYPTED_PRIVATEKEY_INFO_FAILED: "PKCS#8 メッセージの生成に失敗しました。",
            ServiceError_CMP_UPDATE_SAVE_CERTIFICATE_FAILED: "認証書の保存に失敗しました。",
            ServiceError_CMP_UPDATE_INVALID_PASSWORD: "古い認証書のパスワードを確認してください。",
            ServiceError_CMP_UPDATE_IMPORT_INIT_FAILED: "発行された認証書の保存に失敗しました。（initialize失敗）",
            ServiceError_CMP_UPDATE_IMPORT_SIGN_CERTIFICATE_IMPORT_FAILED: "発行された電子署名用認証書の保存に失敗しました。",
            ServiceError_CMP_UPDATE_IMPORT_KM_CERTIFICATE_IMPORT_FAILED: "発行された鍵配布用認証書の保存に失敗しました。",
            ServiceError_CMP_UPDATE_IMPORT_CA_PUB_IMPORT_FAILED: "CAの公開鍵の保存に失敗しました。",
            ServiceError_CMP_UPDATE_IMPORT_FINAL_FAILED: "発行された認証書の保存に失敗しました。（initialize失敗）",
            ServiceError_CMP_UPDATE_NOT_SUPPORTED_BILL: "有料発行はサポートされていません。",
            ServiceError_CMP_UPDATE_NOT_UPDATE_TIME: "認証書を更新することができる期間はありません。認証書の更新は有効期限が切れ、1ヶ月前から可能です。",
            ServiceError_CMP_UPDATE_INVALID_PIN: "PIN番号を確認してください。",

            ServiceError_CMP_REVOKE_INVALID_ARGUMENT: "認証書廃棄に入力した値は正しくありません。",
            ServiceError_CMP_REVOKE_NOT_SUPPORTED_CA: "サポートできない認証機関コードが入力されました。",
            ServiceError_CMP_REVOKE_INPUTPIN_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CMP_REVOKE_EXPORT_CERTIFICATE_AND_KEY_FAILED: "廃棄する認証書のインポートに失敗しました。",
            ServiceError_CMP_REVOKE_ADD_OLD_CERTIFICATE_FAILED: "廃棄する認証書の追加に失敗しました。",
            ServiceError_CMP_REVOKE_ADD_OLD_KEY_FAILED: "廃棄するキーファイルの追加に失敗しました。",
            ServiceError_CMP_REVOKE_INVALID_PASSWORD: "パスワードを確認してください。",
            ServiceError_CMP_REVOKE_INVALID_PIN: "PIN番号を確認してください。",
            ServiceError_CMP_REVOKE_PIN_LOCKED: "PINがロックされています。",

            ServiceError_GET_PCIDENTITY_FAILED_MEMORY_ALLOCATION_FAILED: "メモリの割り当てに失敗しました。",
            ServiceError_GET_PCIDENTITY_FAILED_INVALID_WINDOWS: "PC識別値をインポートできませんでした。(Windows以外の他のOSは、今後サポートします。)",
            ServiceError_GET_PCIDENTITY_FAILED: "PC識別値をインポートできませんでした。(その他のエラー)",

            ServiceError_CHANGE_PIN_FAILED_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_CHANGE_PIN_FAILED_INPUT_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_CHANGE_PIN_FAILED_INVALID_CERT_TYPE: "パスワードの変更に失敗しました。(認証書の形式に問題が発生しました。)",
            ServiceError_CHANGE_PIN_FAILED_PIN_INCORRECT: "パスワードの変更に失敗しました。(パスワードを確認してください。）",
            ServiceError_CHANGE_PIN_FAILED_FILE_WRITE_ERROR: "パスワードの変更に失敗しました。(認証書を保存する時に問題が発生しました。）",
            ServiceError_CHANGE_PIN_FAILED: "パスワードの変更に失敗しました。(その他のエラー）",
            ServiceError_CHANGE_PIN_FAILED_PROGRAM_FILES_PATH_WARNING: "Program filesに格納された証明書のパスワードは変更できません。",

            ServiceError_EXPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "パスワードの入力をキャンセルしました。",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "認証書のエクスポートをキャンセルしました。",
            ServiceError_EXPORT_CERTIFICATE_FAILED_INVALID_CERT_TYPE: "認証書のエクスポートに失敗しました。(認証書の形式に問題が発生しました。)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_SEARCH_CERTIFICATE_FAILED: "認証書のエクスポートに失敗しました。(認証書の検索に失敗しました。)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PIN_INCORRECT: "認証書のエクスポートに失敗しました。(パスワードを確認してください。)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_PWD_INCORRECT: "認証書のエクスポートに失敗しました（パスワードを確認してください）。",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ADD_CERTIFICATELIST_FAILED: "認証書のエクスポートに失敗しました。(add certificate fail)",
            ServiceError_EXPORT_CERTIFICATE_FAILED_ENCODE_PFX_FAILED: "認証書のエクスポートに失敗しました。(encode pfx fail)",
            ServiceError_EXPORT_CERTIFICATE_FAILED: "認証書のエクスポートに失敗しました。(その他のエラー)",

            ServiceError_IMPORT_CERTIFICATE_FAILED_SELECT_CANCELED: "認証書のインポートに失敗しました。(認証書選択をキャンセルしました。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INPUT_CANCELED: "認証書のインポートに失敗しました。(パスワードの入力をキャンセルしました。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX: "認証書のインポートに失敗しました。(PFX形式の認証書ではありません。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED_INVALID_PFX_PASSWORD: "認証書のインポートに失敗しました。(パスワードを確認してください。)",
            ServiceError_IMPORT_CERTIFICATE_FAILED: "認証書のインポートに失敗しました。(その他のエラー)",

            ServiceError_VERIFY_PIN_FAILED_INVALID_CERTINDENTIFIER: "パスワードの確認に失敗しました（入力値が間違っていた）。",
            ServiceError_VERIFY_PIN_FAILED_INPUT_CANCELED: "パスワードの確認に失敗しました（入力をキャンセルしました）。",
            ServiceError_VERIFY_PIN_FAILED: "パスワードの確認に失敗しました（パスワードを確認してください）。",

            ServiceError_CHANGE_STORAGE_FAILED_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_CHANGE_STORAGE_FAILED_INVALID_TOKENINDENTIFIER: "使用できない媒体です。",
            ServiceError_CHANGE_STORAGE_FAILED_INPUT_CANCELED: "認証書の保存媒体の変更に失敗しました。(パスワードの入力をキャンセルしました。)",
            ServiceError_CHANGE_STORAGE_FAILED_CERTIFICATE_AND_KEY_FAILED: "認証書の保存媒体の変更に失敗しました。",
            ServiceError_CHANGE_STORAGE_FAILED_PIN_INCORRECT: "認証書の保存媒体の変更に失敗しました。(パスワードを確認してください。)",
            ServiceError_CHANGE_STORAGE_FAILED_PWD_INCORRECT: "認証書の保存媒体の変更に失敗しました（パスワードを確認してください）。",
            ServiceError_CHANGE_STORAGE_SAME_TOKEN: "変更する認証書の保存媒体が同じです。",
            ServiceError_CHANGE_STORAGE_FAILED: "認証書の保存媒体の変更に失敗しました。(その他のエラー)",

            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTINDENTIFIER: "入力した値は正しくありません。",
            ServiceError_VALIDATE_CERTIFICATE_INVALID_CERTIFICATE: "認証書の形式が正しくありません。",
            ServiceError_VALIDATE_CERTIFICATE_CRL_FAILED: "認証書の検証に失敗しました。",
            ServiceError_VALIDATE_CERTIFICATE_FAILED: "認証書の有効性検証に失敗しました。(その他のエラー)",

            ServiceError_SESSION_MANAGER_SESSION_ID_IS_NULL: "セッションIDが存在しません。セッションの保存に失敗しました。",

            ServiceError_OPERATE_TRAY_INVALID_TRAY_VENDOR: "トレイリストが正しくありません。",
            ServiceError_OPERATE_TRAY_INVALID_TRAY_OPERATE: "トレイの動作が有効ではありません。",

            ServiceError_VERIFY_SIGNATURE_INVALID_ARGUMENT: "入力した値は正しくありません。",
            ServiceError_VERIFY_SIGNATURE_PLAIN_IS_NULL: "元電子文書が必要な電子署名です。",
            ServiceError_VERIFY_SIGNATURE_UNSUPPORT_SIGNTYPE: "まだサポートされていない電子署名です。",
            ServiceError_VERIFY_SIGNATURE_INVALID_X509_TYPE: "X509認証書の形態ではありません。",
            ServiceError_VERIFY_SIGNATURE_INVALID_PUBLIC_KEY_TYPE: "公開鍵の形態ではありません。",
            ServiceError_VERIFY_SIGNATURE_VERIFY_FAILED: "署名検証に失敗しました。",
            ServiceError_VERIFY_SIGNATURE_FILE_READ_FAILED: "ファイルの読み取りに失敗しました。",
            ServiceError_VERIFY_SIGNATURE_FILE_WRITE_FAILED: "ファイルの書き込みに失敗しました。",

            ServiceError_VERIFY_VID_INVALID_CERTID: "入力値が間違っています。",
            ServiceError_VERIFY_VID_INVALID_KEYID: "入力値が間違っています。",
            ServiceError_VERIFY_VID_INVALID_IDN: "入力値が間違っています。",
            ServiceError_VERIFY_VID_TOKEN_NOT_INITIALIZE: "セキュリティディスクが初期化されていませんでした。",
            ServiceError_VERIFY_VID_NOT_FOUND: "入力値が間違っています。",
            ServiceError_VERIFY_VID_NOT_INVALID_X509_TYPE: "X509認証書の形がありません。",
            ServiceError_VERIFY_VID_GET_RANDOM_FAILED: "randomを持ってくるのに失敗しました。",
            ServiceError_VERIFY_VID_INVALID_PWD: "randomを持ってくるのに失敗しました（パスワードを確認してください）。",
            ServiceError_VERIFY_VID_VERIFY_FAILED: "VID検証に失敗しました。",

            /* cipher, hash */
            ServiceError_GET_HASH_FAILED_INVALID_INPUT: "message digestを失敗しました（入力値が間違っていた）。",
            ServiceError_GET_HASH_FAILED_INVALID_ALGORITHM: "message digestを失敗しました（サポートしていないアルゴリズムです）。",
            ServiceError_GET_HASH_FAILED_UNSUPPORTED_DIGEST_ALGORITHM: "message digestを失敗しました（サポートしていないアルゴリズムです）。",
            ServiceError_GET_HASH_FAILED_FILE_NOT_FOUND: "message digestを失敗しました（ファイルが見つかりません）。",
            ServiceError_GET_HASH_FAILED_FILE_READ_FAILED: "message digestを失敗しました（ファイルを読むにエラーが発生しました）。",
            ServiceError_GET_HASH_FAILED: "message digestを失敗しました（その他のエラー）。",

            /* cipher, encrypt */
            ServiceError_ENCRYPT_FAILED_INVALID_INPUT: "message暗号化に失敗しました(入力値が間違っています)。",
            ServiceError_ENCRYPT_FAILED_KEY_IS_NULL: "message暗号化に失敗しました(keyの値がありません)。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_KEY_LEN: "message暗号化に失敗しました(keyの長さが間違っています)。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_ALGORITHM: "message暗号化に失敗しました(サポートしていない暗号化アルゴリズムです)。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_MODE: "message暗号化に失敗しました(サポートしていない動作モードです)。",
            ServiceError_ENCRYPT_FAILED_UNSUPPORTED_PADDING: "message暗号化に失敗しました(サポートしていないパディングです)。",
            ServiceError_ENCRYPT_FAILED: "message暗号化に失敗しました（その他のエラー）。",

            /* cipher, decrypt */
            ServiceError_DECRYPT_FAILED_INVALID_INPUT: "message復号化に失敗しました(入力値が間違っています)。",
            ServiceError_DECRYPT_FAILED_KEY_IS_NULL: "message復号化に失敗しました(keyの値がありません)。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_KEY_LEN: "message復号化に失敗しました(keyの長さが間違っています)。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_ALGORITHM: "message復号化に失敗しました(サポートしていない暗号化アルゴリズムです)。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_MODE: "message復号化に失敗しました(サポートしていない動作モードです)。",
            ServiceError_DECRYPT_FAILED_UNSUPPORTED_PADDING: "message復号化に失敗しました(サポートしていないパディングです)。",
            ServiceError_DECRYPT_FAILED: "message復号化に失敗しました(その他のエラー)。",

            /* envelope */
            ServiceError_ENVELOPE_FAILED_INVALID_INPUT: "電子封筒を失敗しました(入力値が間違っています)。",
            ServiceError_ENVELOPE_FAILED_INVALID_X509_CERT: "電子封筒を失敗しました(入力された認証書がx509はありません)。",
            ServiceError_ENVELOPE_FAILED: "電子封筒を失敗しました(その他のエラー)。",

            /* deenvelope */
            ServiceError_DEENVELOPE_FAILED_INVALID_INPUT: "子封筒復号化に失敗しました(入力値が間違っています)。",
            ServiceError_DEENVELOPE_FAILED_INPUT_CANCELED: "子封筒復号化に失敗しました(入力をキャンセルしました)。",
            ServiceError_DEENVELOPE_FAILED_PIN_INCORRECT: "子封筒復号化に失敗しました(PIN番号を確認してください)。",
            ServiceError_DEENVELOPE_FAILED_PWD_INCORRECT: "子封筒復号化に失敗しました(パスワードをキャンセルしました)。",
            ServiceError_DEENVELOPE_FAILED: "子封筒復号化に失敗しました。",

            /* p12 */
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED: "PFX電子署名に失敗しました（他のエラー）。",
            ServiceError_GET_CERTIFICATE_LIST_P12_FAILED_SELECT_CANCELED: "PFX電子署名に失敗しました（選択を解除しました）。",

            ServiceError_KEYBOARDPROTECTION_INVALID_ARGUMENT: "入力した値は正しくありません。",
            ServiceError_KEYBOARDPROTECTION_CREATE_FAILED: "セキュリティキーボードの実行に失敗しました。",
            ServiceError_KEYBOARDPROTECTION_INIT_FAILED: "セキュリティキーボードの初期化に失敗しました。",
            ServiceError_KEYBOARDPROTECTION_GETPIN_FAILED: "入力した値の取得に失敗しました。(セキュリティキーボード実行エラー)",
            ServiceError_KEYBOARDPROTECTION_GETPUBLICKEY_FAILED: "セキュリティキーボードの公開鍵の取得に失敗しました。",

            ServiceError_MOBILE_USIM_NOT_PRESENT: "スマート認定プログラムがインストールされていませんでした。",
            ServiceError_MOBILE_USIM_INVALID_OPTIONS: "スマート認証のオプションが間違っています。",
            ServiceError_MOBILE_USIM_USER_CANCELED: "スマート認証が取り消されました。",

            ServiceError_RELAY_RAON_NEED_CALL_GETREFNUM: "認証書のコピーに失敗しました（認証番号要求が必要になります）。",
            ServiceError_RELAY_RAON_FAILED_TO_GETREFNUM: "認証書のコピーに失敗しました（認証番号要求に失敗しました）。",
            ServiceError_RELAY_RAON_FAILED_NO_CERT: "認証書のコピーに失敗しました（送信された認証書がありません）。",
            ServiceError_RELAY_RAON_FAILED_TO_GETCERT: "認証書のコピーに失敗しました（送信端末に認証番号を入力してください）。",
            ServiceError_RELAY_RAON_FAILED_TO_EXPORTCERT: "認証書のコピーに失敗しました（認証書のエクスポートに失敗しました）。",
            ServiceError_RELAY_RAON_FAILED_AUTHORIZATION_FAILE: "認証書のコピーに失敗しました（認証番号が一致しません）。",
            ServiceError_RELAY_RAON_NOT_SUPPORTED_TOKEN: "認証書のコピーに失敗しました（サポートされていないトークンです）。",

            /* SCRIPT */
            ScriptError_SETMATCHEDCONTEXT_NOT_CERTIFICATE: "リクエストされた証明書がありません。",
            ScriptError_SIGNDATAB64_NOT_PLAINTEXT: "入力パラメータがnullです。",
            ScriptError_CERTSERVICESETUP_VERSION_MODULE: "インストールしたモジュールのバージョンが低くなります。最新バージョンをインストールしてください。",
            ScriptError_CERTSERVICESETUP_VERSION_DLL: "インストールしたDLLのバージョンが低くなります。最新バージョンをインストールしてください。",
            ScriptError_UCPIDREQUSER_NOT_USERAGREEMENT: "入力パラメータがnullです。 （userAgreement）",
            ScriptError_UCPIDREQUSER_NOT_MODE: "入力パラメーターが無効です。 （mode）",
            ScriptError_PFX_MAKESIGNATURE_ERORR: "電子署名中にエラーが発生しました。"
        }
    ];

    //var errorMessage = (function () {
    //    var message;
    //
    //
    //    return {
    //        setLanguage: function(type){
    //            _mode = type;
    //            message = language[_mode];
    //        },
    //
    //        getMessage: function(value){
    //            for (var i in message) {
    //                if (i == value) {
    //                    return message[i];
    //                }
    //            }
    //            return "undefined";
    //        }
    //    }
    //})();
    function getBrwoserLang() {
        if (typeof (window.navigator.browserLanguage) === 'undefined')
            return window.navigator.language.toLowerCase();
        return window.navigator.browserLanguage.toLowerCase();
    };
    
    var getErrorMessage = function(code, mode){
        var _mode = (typeof(mode) == 'undefined' || mode == '') ? type[getBrwoserLang()] : mode;
        var _message = language[_mode];
        for(var i in _message) {
            if(vest.error.errorCode[i] == code) {
                return _message[i];
            }
        }
        return 'undefined';
    };

   

    if (vest) {
        //vest.error.errorMessage = vest.error.errorMessage || (vest.error.errorMessage = {});
        //errorMassage.setLanguage(type[brwoserLang()]);
        //vest.extend(vest.error, {
        //    'errorMessage': errorMassage
        //});
        vest.error.getErrorMessage = getErrorMessage;
    }
})(window, vest);
package utilities;

public class Constants {

    public static final String AUTH = "Authorization";
    public static final String APPLICATION_JSON = "application/json";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String LIVENESS = "LIVENESS";
    public static final String CNI = "CNI";
    public static final String ISRAEL_ID = "Israel ID";
    public static final String ISRAEL_DRIVING_LICENCE = "Israel Driving License";
    public static final String DK_DRIVING_LICENCE = "Denmark Driving License";
    public static final String PHILIPPINES_CHEQUE = "Philippines Cheque";
    public static final String IL_ID = "IL-ID";

    public static final String LIBRARY_NAME = "libraryName";
    public static final String MATHILDA_CONFIG_NAME = "mathilda.json";
    public static final String QA_CONFIG_JSON = "qa_config.json";
    public static final String FLOW_CONFIG_NAME = "flowConfigName";

    public static final String BEARER = "Bearer";
    public static final String FRAME = "frame";
    public static final String FRAME_REQUEST = "frame_request";
    public static final String IMAGE_JPG = "image/jpg";
    public static final String MESSAGE_TYPE = "message_type";
    public static final String CASE_ID = "caseId";

    public static final double CARD_RATIO = 0.800000011920929;
    public static final double BAR_FACE_SCORE = 0.917608916759491;
    public static final double THRESHOLD_SCORE = 0.1;

    public static final String SUCCESS = "success";
    public static final String STAGE = "stage";
    public static final String MESSAGE = "message";
    public static final String TIMEOUT = "timeout";
    public static final String COMPLETE = "complete";
    public static final String SERVER_ERROR = "ServerError";
    public static final String LAST_RECEIVED_IMAGE = "last_received_image";
    public static final String FACE_CAPTURE_RESUlt = "face_capture_result";
    public static final String DONE = "done";
    public static final String RIGHT = "right";
    public static final String LOOK_RIGHT = "Look right";
    public static final String LEFT = "left";
    public static final String LOOK_LEFT = "Look left";
    public static final String CENTER = "center";
    public static final String OTP = "otp";
    public static final String TRY_AGAIN = "Try again";
    public static final String LOOK_STRAIGHT = "Look straight";
    public static final String NO_FACE_FOUND = "No face found";
    public static final String CASE_ID_NOT_SUPPLIED = "'caseId' was not supplied";
    public static final String NON_LEGIT_LIBRARY_NAME = "Non-legit libraryName supplied";
    public static final String EXPECTING_REQUEST_TYPE_APPLICATION_JSON = "Expecting a request of type 'application/json'";
    public static final String EXPECTING_REQUEST_TYPE_MULTIPART_FORM_DATA = "Expecting a request of type 'multipart/form-data'";
    public static final String INVALID_TOKEN = "Invalid token";
    public static final String TOO_MANY_FACES = "Too many faces";
    public static final String TOKEN_WAS_NOT_PROVIDED = "Token was not provided";
    public static final String NO_FRAME_RECEIVED = "No frame received";
    public static final String WRONG_STRUCTURE = "Error reporting wrong structure, correct structure: {errorCode: number, errorMessage: string}";
    public static final String FORM_MUST_HAVE_FIELD_MESSAGE_TYPE = "Form must have a field 'message_type'";
    public static final String MESSAGE_TYPE_IS_NOT_ALLOWED = "Specified 'message_type' is not one of the allowed: frame_request,report_failure,audio_chunk_request,report_stage_ending";
    public static final String FILE_IS_MISSING = "Content length is either zero or not existent";
    public static final String FILE_CONTENT_IS_EMPTY = "File content was found to be empty";
    public static final String MESSAGE_TRANSLATIONS = "{99=שיק במסגרת, 160=התמונה גדולה מדי, 150=הימנעו מתזוזה, 161=יש בעיה בתמונה, 151=הזיזו את הראש שמאלה, 162=יש להחזיק את הטלפון ישר, 152=הזיזו את הראש ימינה, 163=הישארו במסגרת, 153=הביטו ישר, 164=תמונה התקבלה, 154=התרחקו מהמצלמה, 100=צד לא נכון, 155=חושב על זה..., 101=לא נמצא כרטיס, 156=לא נמצאו פנים, 102=הכרטיס לא במסגרת, 157=יותר מדי פרצופים, 103=הכרטיס זוהה, 158=הפנים שלך חתוכות, 104=הכרטיס רחוק מדי, 159=התקרבו למצלמה, 97=לא נמצא שיק, 98=שיק לא במסגר,}";
    public static final String PRE_STAGES_TRANSLATIONS = "{CENTER=הביטו ישר, DONE=סיימתם בהצלחה!, PASSIVE=מקמו את הפנים במסגרת, LEFT=הזיזו את הראש שמאלה, FINISH_RECORD=סיום הקלטה, RECORD_OTP=קראו את הקוד בקול, RIGHT=הזיזו את הראש ימינה, ERROR=שגיאה, RECORD_STT=קראו את הטקסט בקו,}";
    public static final String STYLES = "{recordingButtonTitleFontFamily=Assistant SemiBold, serif, instructionLabelBorderRadius=8px, progressLabelFontFamily=Assistant SemiBold, serif, instructionLabelBg=#3346FF, progressLabelColor=#fff, instructionLabelColor=#fff, instructionLabelFontFamily=Assistant SemiBold, serif, recordingNumbersFontFamily=Assistant Bold, serif, scanLineColor=#0A67FECC, direction=rtl, progressLabelOpacity=0.46, instructionLabelContainerBg=transparen,}";
    public static final String STAGES_ACTIVE = "[passive, right, center, left, otp]";
    public static final String STAGES_PASSIVE = "[passive]";
    public static final String PASSIVE = "passive";
    public static final String EMPTY_LIST = "[]";
    public static final String EMPTY_STRING = "";


    public static final String ACTION_TYPE = "action_type";
    public static final String THRESHOLD = "threshold";
    public static final String STATUS = "status";
    public static final String CONFIG_FILE_NAME = "configFilename";
    public static final String SCORE = "score";
    public static final String FACE_IMAGE = "face_image";
    public static final String NAME = "name";
    public static final String USER_CANCELED = "UserCanceled";
    public static final String USER_PRESSED_CANCEL = "User pressed cancel";
    public static final String PROCESS_ENDED_WITH_FAILURE = "Process ended with failure";
    public static final String CODE = "code";


    //MongoDB constants
    public static final String EVENTS = "events";
    public static final String SESSION_START = "session_start";
    public static final String FAILURE = "failure";
    public static final String MODULAR_SERVER_LIVENESS = "modular-server-liveness";
    public static final String MULTI = "multi";
    public static final String MATHILDA_JSON = "mathilda.json";
    public static final String IMAGE_INJECTION_DETECTED = "Image injection attempt detected";
    public static final String FACE_FOUND_TO_BE_REAL = "Process ended successfully";
    public static final String SESSION_REACHED_MAX_ALLOW_TIMEOUT = "Session reached maximum allowed flow timeout";
    public static final String TIMESTAMP = "timestamp";


    public static final String FACE_TO_SMALL = "Face too small";
    public static final String FACE_TO_CLOSE = "Face too close";
    public static final String COME_CLOSER = "Come closer";
    public static final String NO_UPCOMING_STAGES = "No upcoming stages";
    public static final String CARD_DETECTED = "Card detected";


    public static final String ERROR_MESSAGE = "errorMessage";
    public static final int MESSAGE_ID_103 = 103;
    public static final int MESSAGE_ID_151 = 151;
    public static final int MESSAGE_ID_152 = 152;
    public static final int MESSAGE_ID_153 = 153;
    public static final int MESSAGE_ID_154 = 154;
    public static final int MESSAGE_ID_156 = 156;
    public static final int MESSAGE_ID_157 = 157;
    public static final int MESSAGE_ID_158 = 158;
    public static final int MESSAGE_ID_159 = 159;
    public static final int MESSAGE_ID_161 = 161;

    public static final String ERROR_CODE = "errorCode";
    public static final int ERROR_CODE_1000 = 1000;
    public static final int ERROR_CODE_1001 = 1001;
    public static final int ERROR_CODE_1002 = 1002;
    public static final int ERROR_CODE_1005 = 1005;
    public static final int ERROR_CODE_1006 = 1006;
    public static final int ERROR_CODE_1008 = 1008;
    public static final int ERROR_CODE_1009 = 1009;
    public static final int ERROR_CODE_1010 = 1010;
    public static final int ERROR_CODE_1011 = 1011;
    public static final int ERROR_CODE_1013 = 1013;
    public static final int ERROR_CODE_1014 = 1014;
    public static final int ERROR_CODE_1015 = 1015;
    public static final int ERROR_CODE_1105 = 1105;
    public static final int ERROR_CODE_2010 = 2010;


}

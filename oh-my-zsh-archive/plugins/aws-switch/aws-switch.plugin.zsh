function aws-switch() {
    case ${1} in
        "")
            unset AWS_PROFILE
            ;;
        clear)
            unset AWS_PROFILE
            ;;
        *)
            export AWS_PROFILE="${1}"
            ;;
    esac
}

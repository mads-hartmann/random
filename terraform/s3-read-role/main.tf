#
# Basics
#

terraform {
  required_version = ">= 0.11.8"
}

provider "aws" {
  region = "eu-central-1"
  version = "1.40"
}

#
# The bucket
#

resource "aws_s3_bucket" "example-bucket" {
  bucket = "hartmann.example.bucket"
  acl    = "private"

  tags {
    Name        = "My bucket"
    Environment = "Dev"
  }
}

#
# The user
#

resource "aws_iam_user" "example-user" {
  name = "example-user"
}

resource "aws_iam_access_key" "example-user-accesskey" {
  user    = "${aws_iam_user.example-user.name}"
  pgp_key = "keybase:mads_hartmann"
}

resource "aws_iam_group" "example-group" {
  name = "example-group"
}

resource "aws_iam_group_membership" "example-group-team" {
  name = "example-group-membership"
  group = "${aws_iam_group.example-group.name}"
  users = [
    "${aws_iam_user.example-user.name}"
  ]
}

resource "aws_iam_group_policy_attachment" "example-group-readonly-attachment" {
  group      = "${aws_iam_group.example-group.name}"
  policy_arn = "${aws_iam_policy.readonly-policy.arn}"
}


resource "aws_iam_policy" "readonly-policy" {
    name        = "example-read-policy"
    description = "This policy gives the user access to get objects in the ${aws_s3_bucket.example-bucket.bucket}"
    policy = <<EOF
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:ListBucket"
            ],
            "Resource": "${aws_s3_bucket.example-bucket.arn}"
        },
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject"
            ],
            "Resource": "${aws_s3_bucket.example-bucket.arn}/cleaned-*"
        }
    ]
}
EOF
}

#
# Output.
# Just to make it easier to see if things work.
#
output "AWS_ACCESS_KEY_ID" {
  value = "${aws_iam_access_key.example-user-accesskey.id}"
}

output "AWS_SECRET_ACCESS_KEY" {
  value = "${aws_iam_access_key.example-user-accesskey.encrypted_secret}"
}

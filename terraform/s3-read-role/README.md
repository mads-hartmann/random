A simple setup with

- A private bucket
- A policy for reading the contents of the bucket
- A group, with the policy attached
- A user, who is part of the group

## Create

```sh
terraform init
terraform validate
terraform plan
terraform apply
```

Upload some data

```sh
echo "what" >> database-dump.txt
echo "Clean" >> cleaned-database-dump.txt
aws s3 cp database-dump.txt s3://hartmann.example.bucket/database-dump.txt
aws s3 cp cleaned-database-dump.txt s3://hartmann.example.bucket/cleaned-database-dump.txt
rm database-dump.txt
rm cleaned-database-dump.txt
```

## Test

To verify that everything works we'll try to download the objects
using the newly created IAM user.

Run the following:

```sh
export AWS_ACCESS_KEY_ID=$(terraform output AWS_ACCESS_KEY_ID)
export AWS_SECRET_ACCESS_KEY=$(terraform output AWS_SECRET_ACCESS_KEY | base64 --decode | keybase pgp decrypt)
```

Now run the following two commands:

```sh
# Should work
aws s3 cp s3://hartmann.example.bucket/cleaned-database-dump.txt /tmp/cleaned-database-dump.txt
# Should give 'fatal error: An error occurred (403) when calling the HeadObject operation: Forbidden'
aws s3 cp s3://hartmann.example.bucket/database-dump.txt /tmp/database-dump.txt
```

## Destroy

When you're done

```sh
terraform destroy
```

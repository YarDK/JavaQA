<?php

# --- Database Configuration ---
$g_hostname               = 'localhost';
$g_db_type                = 'mysqli';
$g_database_name          = 'bugtracker';
$g_db_username            = 'root';
$g_db_password            = '';

# --- Security ---
$g_default_timezone       = 'Europe/Berlin';
$g_crypto_master_salt     = 'n5QSqrJAvuG9OZ7bcnNGCKrdpruLM2Vmmn5Zm53uHBY=';

# --- Anonymous Access / Signup ---
$g_allow_signup				= ON;
$g_allow_anonymous_login	= OFF;
$g_anonymous_account		= '';
$g_signup_use_captcha       = OFF;

# --- Email Configuration ---
$g_phpMailer_method		= PHPMAILER_METHOD_MAIL; # or PHPMAILER_METHOD_SMTP, PHPMAILER_METHOD_SENDMAIL
$g_smtp_host			= 'localhost';			# used with PHPMAILER_METHOD_SMTP
package com.project.cucionline;

import android.provider.BaseColumns;

public class DatabaseLogin {
    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_NAMA_USER = "nama_user";
        public static final String COLUMN_ALAMAT_USER = "alamat_user";
        public static final String COLUMN_NOTELP_USER = "notelp_user";
        public static final String COLUMN_EMAIL_USER = "email_user";
        public static final String COLUMN_PASSWORD_USER = "password_user";
        public static final String COLUMN_MOTOR = "jenis_motor";
    }
}

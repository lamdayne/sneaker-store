CREATE DATABASE sneaker_store;

CREATE TABLE users(
                      id VARCHAR(255) PRIMARY KEY,
                      full_name VARCHAR(200) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      phone VARCHAR(20) UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      avatar_url TEXT,
                      role ENUM('USER', 'ADMIN', 'STAFF'),
                      is_active BOOLEAN NOT NULL DEFAULT TRUE,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE addresses(
                          id VARCHAR(255) PRIMARY KEY,
                          user_id VARCHAR(255) NOT NULL,
                          recipient_name VARCHAR(100) NOT NULL,
                          phone VARCHAR(20) NOT NULL,
                          province VARCHAR(100) NOT NULL,
                          district VARCHAR(100) NOT NULL,
                          ward VARCHAR(100) NOT NULL,
                          street_address TEXT NOT NULL,
                          is_default BOOLEAN NOT NULL DEFAULT false,
                          FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE categories(
                           id VARCHAR(255) PRIMARY KEY,
                           parent_id VARCHAR(255) NULL DEFAULT NULL,
                           name VARCHAR(255) NOT NULL,
                           slug VARCHAR(255) UNIQUE,
                           image_url TEXT,
                           display_order INT NOT NULL DEFAULT 0,
                           is_active BOOLEAN NOT NULL DEFAULT TRUE,
                           FOREIGN KEY (parent_id) REFERENCES categories(id)
);

CREATE TABLE brands(
                       id VARCHAR(255) PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       slug VARCHAR(255) UNIQUE,
                       logo_url TEXT,
                       description TEXT,
                       is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE products(
                         id VARCHAR(255) PRIMARY KEY,
                         category_id VARCHAR(255) NOT NULL,
                         brand_id VARCHAR(255) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         slug VARCHAR(255) UNIQUE,
                         description TEXT DEFAULT NULL,
                         base_price DOUBLE NOT NULL,
                         material VARCHAR(255) DEFAULT NULL,
                         is_featured BOOLEAN DEFAULT FALSE,
                         is_active BOOLEAN DEFAULT TRUE,
                         created_at TIMESTAMP,
                         FOREIGN KEY (category_id) REFERENCES categories(id),
                         FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE TABLE product_variants(
                                 id VARCHAR(255) PRIMARY KEY,
                                 product_id VARCHAR(255) NOT NULL,
                                 size VARCHAR(10) NOT NULL,
                                 color VARCHAR(255) NOT NULL,
                                 color_hex VARCHAR(10) NULL DEFAULT NULL,
                                 stock_quantity INT NOT NULL DEFAULT 0,
                                 price_override DOUBLE NULL DEFAULT NULL,
                                 is_active BOOLEAN NOT NULL DEFAULT TRUE,
                                 FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE product_images(
                               id VARCHAR(255) PRIMARY KEY,
                               product_id  VARCHAR(255) NOT NULL,
                               variant_id VARCHAR(255) NULL DEFAULT NULL,
                               image_url TEXT NOT NULL,
                               is_primary BOOLEAN NOT NULL DEFAULT FALSE,
                               display_order INT NOT NULL DEFAULT 0,
                               FOREIGN KEY (product_id) REFERENCES products(id),
                               FOREIGN KEY (variant_id) REFERENCES product_variants(id)
);

CREATE TABLE carts(
                      id VARCHAR(255) PRIMARY KEY,
                      user_id VARCHAR(255) NULL DEFAULT NULL,
                      expires_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items(
                           id VARCHAR(255) PRIMARY KEY,
                           cart_id VARCHAR(255) NOT NULL,
                           variant_id VARCHAR(255) NOT NULL,
                           quantity INT NOT NULL DEFAULT 1,
                           unit_price DOUBLE NOT NULL,
                           added_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (cart_id) REFERENCES carts(id),
                           FOREIGN KEY (variant_id) REFERENCES product_variants(id)
);

CREATE TABLE orders(
                       id VARCHAR(255) PRIMARY KEY,
                       user_id VARCHAR(255) NOT NULL,
                       order_code VARCHAR(50) NOT NULL UNIQUE,
                       shipping_address_id VARCHAR(255) NOT NULL,
                       subtotal DOUBLE NOT NULL,
                       shipping_fee DOUBLE NOT NULL DEFAULT 0,
                       discount_amount DOUBLE NOT NULL DEFAULT 0,
                       total_amount DOUBLE NOT NULL,
                       status ENUM('PENDING', 'PROCESSING', 'SHIPPING', 'DELIVERED', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
                       payment_method ENUM('COD', 'BANK_TRANSFER') DEFAULT NULL,
                       payment_status ENUM('UNPAID', 'PAID', 'REFUNDED') NOT NULL DEFAULT 'UNPAID',
                       note TEXT NULL DEFAULT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id),
                       FOREIGN KEY (shipping_address_id) REFERENCES addresses(id)
);

CREATE TABLE order_items(
                            id VARCHAR(255) PRIMARY KEY,
                            order_id VARCHAR(255) NOT NULL,
                            variant_id VARCHAR(255) NULL  DEFAULT NULL,
                            product_name VARCHAR(255) NOT NULL,
                            size VARCHAR(10) NOT NULL,
                            color VARCHAR(50) NOT NULL,
                            quantity INT NOT NULL,
                            unit_price DOUBLE NOT NULL,
                            total_price DOUBLE NOT NULL,
                            FOREIGN KEY (order_id) REFERENCES orders(id),
                            FOREIGN KEY (variant_id) REFERENCES product_variants(id)
)








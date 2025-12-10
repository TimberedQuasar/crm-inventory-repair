-- Tworzenie ENUM typów
CREATE TYPE user_role AS ENUM ('ADMIN', 'MANAGER', 'TECHNICIAN', 'CUSTOMER');
CREATE TYPE machine_status AS ENUM ('OPERATIONAL', 'MAINTENANCE', 'REPAIR', 'RETIRED');
CREATE TYPE repair_priority AS ENUM ('URGENT', 'HIGH', 'MEDIUM', 'LOW');
CREATE TYPE work_order_status AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'ON_HOLD');
CREATE TYPE equipment_condition AS ENUM ('NEW', 'GOOD', 'DAMAGED', 'MISSING');
CREATE TYPE handle_type AS ENUM ('TIG', 'MIG', 'MMA');
CREATE TYPE wire_size AS ENUM ('0,8', '1,0', '1,2');
CREATE TYPE other_equipment_type as ENUM ('ADAPTER', 'Przewód gazowy', 'Reduktor', 'Podgrzewacz gazowy', 'Przewód masowy');

-- Tabela Users
CREATE TABLE users (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    sep_number VARCHAR(255),
    role user_role NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);

-- Tabela Customers
CREATE TABLE customers (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    company_name VARCHAR(200) UNIQUE NOT NULL,
    contact_person VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(50),
    zip VARCHAR(20),
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP
);

-- Tabela Suppliers
CREATE TABLE suppliers (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(200) NOT NULL,
    link VARCHAR(100),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Tabela Machines
CREATE TABLE machines (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    machine_id VARCHAR(100) UNIQUE NOT NULL,
    serial_number VARCHAR(100) UNIQUE,
    model VARCHAR(100),
    manufacturer VARCHAR(100),
    status machine_status,
    welding_handle handle_type,
    wire wire_size,
    other other_equipment_type,
    customer_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

-- Tabela Technicians
CREATE TABLE technicians (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    hire_date DATE,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Tabela Repairs
CREATE TABLE repairs (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    machine_id INT NOT NULL,
    priority repair_priority,
    status work_order_status,
    estimated_hours DECIMAL(5, 2),
    assigned_technician_id INT,
    created_at TIMESTAMP DEFAULT NOW(),
    scheduled_date DATE,
    completed_date DATE,
    updated_at TIMESTAMP,
    FOREIGN KEY (machine_id) REFERENCES machines(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_technician_id) REFERENCES technicians(id) ON DELETE SET NULL
);

-- Tabela Work Orders
CREATE TABLE work_orders (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    repair_id INT NOT NULL,
    description TEXT,
    status work_order_status,
    estimated_labor_hours DECIMAL(5, 2),
    actual_labor_hours DECIMAL(5, 2),
    estimated_cost DECIMAL(10, 2),
    actual_cost DECIMAL(10, 2),
    created_by INT,
    completed_by INT,
    created_at TIMESTAMP DEFAULT NOW(),
    completed_at TIMESTAMP,
    FOREIGN KEY (repair_id) REFERENCES repairs(id) ON DELETE CASCADE,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (completed_by) REFERENCES users(id) ON DELETE SET NULL
);

-- Tabela Work Order Materials (zamiast work_order_parts - bez magazynu)
CREATE TABLE work_order_materials (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    work_order_id INT NOT NULL,
    material_name VARCHAR(200) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    total_price DECIMAL(10, 2),
    supplier_name VARCHAR(200),
    category VARCHAR(100),
    notes TEXT,
    added_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (work_order_id) REFERENCES work_orders(id) ON DELETE CASCADE
);

-- Tabela Machine Equipment
CREATE TABLE machine_equipment (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    machine_id INT NOT NULL,
    equipment_name VARCHAR(200) NOT NULL,
    description TEXT,
    quantity INT DEFAULT 1,
    condition equipment_condition,
    notes TEXT,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP,
    FOREIGN KEY (machine_id) REFERENCES machines(id) ON DELETE CASCADE
);

-- Tabela Documents
CREATE TABLE documents (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    machine_id INT NOT NULL,
    file_path VARCHAR(255),
    file_type VARCHAR(50),
    uploaded_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (machine_id) REFERENCES machines(id) ON DELETE CASCADE
);
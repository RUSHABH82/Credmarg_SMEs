const API_URL = 'http://localhost:8080';

export const getEmployees = async () => {
    const response = await fetch(`${API_URL}/employee`);
    return response.json();
};

export const getVendors = async () => {
    const response = await fetch(`${API_URL}/vendor`);
    return response.json();
};

export const getMessages = async () => {
    const response = await fetch(`${API_URL}/vendor/mail/read`, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: '{}'
    });
    return response.json();

};

export const addEmployee = async (employee) => {
    const response = await fetch(`${API_URL}/employee`, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(employee)
    });
    return response.json();
};

export const addVendor = async (vendor) => {
    const response = await fetch(`${API_URL}/vendor`, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(vendor)
    });
    return response.json();
};

export const sendPaymentMailToVendor = async (filter) => {
    const response = await fetch(`${API_URL}/vendor/mail/send`, {
        method: 'POST', headers: {
            'Content-Type': 'application/json'
        }, body: JSON.stringify(filter)
    });
    return response.json();
};

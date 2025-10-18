export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'tigr@ttweb.org'
        ? resolve({ result: 'success' })
        : reject(new Error('User already exist'))
    })
  }
}

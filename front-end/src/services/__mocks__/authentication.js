export default {
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      (detail.username === 'tigr' || detail.username === 'tigr@ttweb.org') &&
      detail.password === 'Tigr@1996'
        ? resolve({ result: 'success' })
        : reject(new Error('Invalid credentials'))
    })
  }
}

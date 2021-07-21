// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {
  'default e2e tests': browser => {
    browser
      .init()
      .url(process.env.VUE_DEV_SERVER_URL + 'login')
      .waitForElementVisible('#app')
      // .assert.elementPresent('.hello')
      //.assert.containsText('h1', 'Login')
      .assert.elementCount('img', 0)
      .end()
  },

  'example e2e test using a custom command': browser => {
    browser
      .openHomepage()
      .assert.containsText('h1', 'Task Management Application')
      .end()
  }
}

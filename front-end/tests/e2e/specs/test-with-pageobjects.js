/// /////////////////////////////////////////////////////////////
// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide
//
// For more information on working with page objects see:
//   https://nightwatchjs.org/guide/working-with-page-objects/
/// /////////////////////////////////////////////////////////////

module.exports = {
  beforeEach: (browser) => browser.init(),

  'e2e tests using page objects': (browser) => {
    const homepage = browser.page.homepage()
    homepage.waitForElementVisible('@appContainer')

    const app = homepage.section.app
    app.assert.elementCount('@logo', 0)
    // app.expect.section('@welcome').to.be.visible
    // app.expect.section('@headline').text.to.match(/^Welcome to Your Vue\.js (.*)App$/)

    browser.end()
  },

  'verify if string "e2e-nightwatch" is within the cli plugin links': (browser) => {
    const homepage = browser.page.homepage()
    const login = homepage.section.app

    login.expect.element('h1').text.to.contain('Task Management')
  }
}

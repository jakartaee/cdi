<!--
    Copyright (c) 2021 Red Hat, Inc. and others
    
    This program and the accompanying materials are made available under the
    Apache Software License 2.0 which is available at:
    https://www.apache.org/licenses/LICENSE-2.0.
    
    SPDX-License-Identifier: Apache-2.0
-->
# Jakarta CDI Website Based on Jekyll

## Getting Started

These instructions will get you a copy of the eclipse-ee4j.github.io/cdi/ website up and running on your local machine for development and testing purposes.

### Installation
[Jekyll static site generator docs](https://jekyllrb.com/docs/).

1. Install a full [Ruby development environment](https://jekyllrb.com/docs/installation/) (Version 2.7 is recommended, version 3.x seems to be incompatible)
2. Install [bundler](https://jekyllrb.com/docs/ruby-101/#bundler)  [gems](https://jekyllrb.com/docs/ruby-101/#gems)

        gem install bundler

3. Fork the [project repository](https://github.com/eclipse-ee4j/cdi), then clone your fork.

        git clone https://github.com/jakartaee/cdi

4. Change into the project directory:

        cd cdi/docs

5. Use bundler to fetch all required gems in their respective versions

        bundle install

6. Build the site and make it available on a local server

        bundle exec jekyll serve

7. Now browse to http://localhost:4000/cdi/

> If you encounter any unexpected errors during the above, please refer to the [troubleshooting](https://jekyllrb.com/docs/troubleshooting/#configuration-problems) page or the [requirements](https://jekyllrb.com/docs/installation/#requirements) page, as you might be missing development headers or other prerequisites.

**For more regarding the use of Jekyll, please refer to the [Jekyll Step by Step Tutorial](https://jekyllrb.com/docs/step-by-step/01-setup/).**

## Writing a Blog Post

To write a blog:

- create an author entry in [_authors](https://github.com/jakartaee/cdi/tree/master/docs/_authors)
- create a blog entry under [_posts](https://github.com/jakartaee/cdi/tree/master/docs/_posts)
  - the file name is `yyyy-mm-dd-slug.adoc`
- `tag` or `tags` should be used with some care as an archive page is created for of them. Below are some basic rules to follow:
   - `cdi-release` used for CDI release blogs
   - `announcement` used for general announcement with some impact.
   - `development-tips` used for blogs with tips to develop with CDI.
   - tags are space separated list `tags:cdi-release announcement`
   - tags must be in lowercase
- it's in Markdown format, see [2021-10-25-way-to-cdi4.md](https://github.com/jakartaee/cdi/blob/master/docs/_posts/2021-10-25-way-to-cdi4.md) for example
- the `date` attribute in the front matter defines when the article will be published. Use a present date while writing your article to test locally, then switch to the actual target date before submitting.
- send a pull request against the `main` branch and when it is merged it will be automatically incorporated into the site.

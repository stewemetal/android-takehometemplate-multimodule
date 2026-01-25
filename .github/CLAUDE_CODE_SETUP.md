# Claude Code Automation Setup

This repository is configured to use Claude Code for automated issue implementation.

## 🚀 How It Works

1. **Create an issue** using the "Claude Code Task" template
2. **Add the `claude-code` label** to the issue
3. **Claude Code automatically**:
   - Analyzes the issue
   - Implements the solution
   - Runs tests
   - Creates a Pull Request for review

## 📋 Prerequisites

### Required Secrets

Add these secrets to your GitHub repository (Settings → Secrets and variables → Actions):

1. **`ANTHROPIC_API_KEY`** (Required)
   - Get your API key from https://console.anthropic.com/
   - This is used to access Claude Code's AI capabilities
   - **Cost**: Monitor your usage at https://console.anthropic.com/settings/usage

### Permissions

The workflow needs these permissions (already configured):
- ✅ `contents: write` - To create branches and commits
- ✅ `issues: write` - To comment on issues
- ✅ `pull-requests: write` - To create PRs

## 🎯 Usage

### Method 1: Using the Issue Template (Recommended)

1. Go to **Issues** → **New Issue**
2. Select **"Claude Code Task"** template
3. Fill in the required sections:
   - Description (be specific!)
   - Acceptance criteria
   - Expected behavior
   - Constraints
4. Create the issue
5. The `claude-code` label is automatically added
6. Wait for Claude Code to create a PR

### Method 2: Manual Labeling

1. Create any issue
2. Add the `claude-code` label
3. Ensure the issue has clear requirements
4. Claude Code will process it

### Method 3: Comment Trigger

1. Comment `/claude` on any existing issue
2. Claude Code will process the issue

## ✍️ Writing Good Claude Code Issues

Claude Code works best with clear, detailed issues. Follow these guidelines:

### ✅ Good Example

```markdown
## Description
Add a dark mode toggle to the settings screen

## Acceptance Criteria
- [ ] Add a toggle switch in SettingsScreen.kt
- [ ] Toggle persists across app restarts using DataStore
- [ ] Material3 theme switches between light and dark
- [ ] No visual glitches during theme transition

## Expected Behavior
Users can toggle between light and dark themes from the settings screen.
The choice is remembered when the app restarts.

## Constraints
- Use Material3 DynamicColors
- Follow existing architecture patterns (Koin DI, Compose)
- Add unit tests for the theme preference repository
```

### ❌ Bad Example

```markdown
add dark mode
```

**Why it's bad**: Too vague, no acceptance criteria, no technical details.

## 🔧 Configuration

### Rate Limiting

The workflow has built-in rate limiting to control costs:
- **Maximum**: 10 Claude Code tasks per day
- **Timeout**: 30 minutes per task
- Prevents accidental runaway API usage

To adjust these limits, edit `.github/workflows/claude-code-issue-handler.yml`:

```yaml
# Change daily limit (line ~30)
if [ "$COUNT" -gt 10 ]; then

# Change timeout (line ~18)
timeout-minutes: 30
```

### Cost Management

Claude Code uses the Anthropic API, which has usage costs:

- **Monitor usage**: https://console.anthropic.com/settings/usage
- **Set budget alerts**: Configure in Anthropic Console
- **Typical cost**: ~$0.10-$1.00 per issue (varies by complexity)

**Tips to reduce costs**:
1. Write detailed issues to reduce back-and-forth
2. Test locally before using automation for simple tasks
3. Use rate limiting (already configured)
4. Review and close duplicate issues promptly

## 🏷️ Labels

The workflow uses these labels:

- `claude-code` - Triggers the automation
- `claude-in-progress` - Added while processing
- `automated` - Added to generated PRs
- `needs-review` - Requires human review

## 🔍 Monitoring

### Check Workflow Status

1. Go to **Actions** tab
2. Select "Claude Code Issue Handler"
3. View recent runs and logs

### Debug Issues

If Claude Code fails:

1. Check the workflow run logs
2. Review the error message in the issue comment
3. Verify API key is valid
4. Check rate limits haven't been exceeded

## 🛡️ Safety Features

The workflow includes several safety measures:

1. **Rate Limiting**: Max 10 tasks/day
2. **Timeout Protection**: 30-minute max per task
3. **Test Validation**: All tests must pass
4. **Human Review Required**: PRs need approval
5. **No Auto-Merge**: Always requires manual review

## 🚨 Important Notes

### When NOT to Use Claude Code

- **Security-critical changes**: Review manually
- **Breaking changes**: Requires careful planning
- **Database migrations**: Need manual verification
- **Simple typo fixes**: Faster to fix manually
- **Unclear requirements**: Clarify first

### Current Limitations

The workflow currently requires manual setup of the Claude Code CLI. Until that's configured, you'll see a comment indicating manual setup is needed.

To complete the setup:

1. Choose a Claude Code CLI installation method
2. Update the "Install Claude Code CLI" step in the workflow
3. Test with a simple issue

## 📖 Examples

See these example issues for reference:

- [Example: Add a new feature screen](#) (Coming soon)
- [Example: Fix a bug](#) (Coming soon)
- [Example: Refactor existing code](#) (Coming soon)

## 🤝 Contributing

To improve this automation:

1. Test with simple issues first
2. Provide feedback on generated PRs
3. Update the workflow as needed
4. Share successful patterns

## 📚 Resources

- [Claude Code Documentation](https://claude.com/claude-code)
- [Anthropic API Docs](https://docs.anthropic.com/)
- [GitHub Actions Docs](https://docs.github.com/en/actions)

## 🆘 Support

If you encounter issues:

1. Check the workflow logs in the Actions tab
2. Review the error messages in issue comments
3. Open a discussion in the repository
4. Contact the maintainer

---

**Note**: This is an experimental feature. Always review generated code carefully before merging.
